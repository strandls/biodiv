package species.utils

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;

import org.apache.commons.logging.LogFactory;

class ImageUtils {

	private static final log = LogFactory.getLog(this);
	
	
	//TODO: chk synchronization probs ... static blocks
	/**
	 * Creates scaled versions of given image in the directory.
	 * Converts image to jpg 
	 * Uses suffixes as defined in Config.
	 * @param imageFile
	 * @param dir
	 */
	static void createScaledImages(File imageFile, File dir) {
		log.debug "Creating scaled versions of image : "+imageFile.getAbsolutePath();
		
		def config = org.codehaus.groovy.grails.commons.ConfigurationHolder.config.speciesPortal.resources.images
		
		log.debug "Creating thumbnail image";
		ImageUtils.convert(imageFile, new File(dir, imageFile.getName().tokenize(".")[0] + config.thumbnail.suffix), config.thumbnail.width, config.thumbnail.height, 100);

		log.debug "Creating gallery image";
		ImageUtils.convert(imageFile, new File(dir, imageFile.getName().tokenize(".")[0] + config.gallery.suffix), config.gallery.width, config.gallery.height, 100);

		log.debug "Creating gallery thumbnail image";
		ImageUtils.convert(imageFile, new File(dir, imageFile.getName().tokenize(".")[0] + config.galleryThumbnail.suffix), config.galleryThumbnail.width, config.galleryThumbnail.height, 100);
	}
	
	/**
	 * Uses a Runtime.exec()to use imagemagick to perform the given conversion
	 * operation. Returns true on success, false on failure. Does not check if
	 * either file exists.
	 *
	 * @param in Description of the Parameter
	 * @param out Description of the Parameter
	 * @param newSize Description of the Parameter
	 * @param quality Description of the Parameter
	 * @return Description of the Return Value
	 */
	private static boolean convert(File inImg, File outImg, int width, int height, int quality) {

		if (!quality || quality < 0 || quality > 100) {
			quality = 75;
		}

		ArrayList command = new ArrayList(10);

		// note: CONVERT_PROG is a class variable that stores the location of ImageMagick's convert command
		// it might be something like "/usr/local/magick/bin/convert" or something else, depending on where you installed it.
		def config = org.codehaus.groovy.grails.commons.ConfigurationHolder.config
		command.add(config.imageConverterProg);
		command.add("-resize");
		command.add(width + "x" + height);
		command.add("-quality");
		command.add("" + quality);
		command.add(inImg.getAbsolutePath());
		command.add(outImg.getAbsolutePath());

		log.debug command;

		def proc = command.execute()                 // Call *execute* on the string
		proc.waitFor()                               // Wait for the command to finish

		// Obtain status and output
		log.debug "return code: ${ proc.exitValue()}"
		log.debug "stderr: ${proc.err.text}"
		log.debug "stdout: ${proc.in.text}" // *out* from the external program is *in* for groovy

		if(proc.exitValue() == 0) {
			jpegOptimize(outImg);
		}
		return (proc.exitValue() == 0)
	}

	/**
	 * Uses a Runtime.exec()to use jpegoptim program to optimize 
	 * size of jpg files by stripping off all meta data
	 *
	 * @param file 
	 * @return Description of the Return Value
	 */
	private static boolean jpegOptimize(File file) {

		ArrayList command = new ArrayList(10);

		def config = org.codehaus.groovy.grails.commons.ConfigurationHolder.config
		command.add(config.jpegOptimProg);
		command.add("--strip-all");
		command.add(file.getAbsolutePath());

		log.debug command;

		def proc = command.execute()                 // Call *execute* on the string
		proc.waitFor()                               // Wait for the command to finish

		log.debug "return code: ${ proc.exitValue()}"
		log.debug "stderr: ${proc.err.text}"
		log.debug "stdout: ${proc.in.text}" // *out* from the external program is *in* for groovy

		return (proc.exitValue() == 0)
	}
	
	
	/**
	 * Convenience method that returns a scaled instance of the
	 * provided {@code BufferedImage}.
	 *
	 * @param img the original image to be scaled
	 * @param targetWidth the desired width of the scaled instance,
	 *    in pixels
	 * @param targetHeight the desired height of the scaled instance,
	 *    in pixels
	 * @param hint one of the rendering hints that corresponds to
	 *    {@code RenderingHints.KEY_INTERPOLATION} (e.g.
	 *    {@code RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR},
	 *    {@code RenderingHints.VALUE_INTERPOLATION_BILINEAR},
	 *    {@code RenderingHints.VALUE_INTERPOLATION_BICUBIC})
	 * @param higherQuality if true, this method will use a multi-step
	 *    scaling technique that provides higher quality than the usual
	 *    one-step technique (only useful in downscaling cases, where
	 *    {@code targetWidth} or {@code targetHeight} is
	 *    smaller than the original dimensions, and generally only when
	 *    the {@code BILINEAR} hint is specified)
	 * @return a scaled version of the original {@code BufferedImage}
	 */
	static BufferedImage getScaledInstance(BufferedImage img,
	int targetWidth,
	int targetHeight,
	boolean higherQuality) {

		int type = (img.getTransparency() == Transparency.OPAQUE) ?
				BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = (BufferedImage)img;
		int w, h;
		if (higherQuality) {
			// Use multi-step technique: start with original size, then
			// scale down in multiple passes with drawImage()
			// until the target size is reached
			w = img.getWidth();
			h = img.getHeight();
		} else {
			// Use one-step technique: scale directly from original
			// size to target size with a single drawImage() call
			w = targetWidth;
			h = targetHeight;
		}

		while(true) {
			if (higherQuality && w > targetWidth) {
				w /= 2;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (higherQuality && h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();

			ret = tmp;

			if (w != targetWidth || h != targetHeight) {
				continue;
			} else {
				break;
			}
		}

		return ret;
	}
}
