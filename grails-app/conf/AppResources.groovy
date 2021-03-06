
//adhoc.patterns.excludes = ["*.css"]
//mappers.hashandcache.excludes = ["**/*.css"]
//

modules = {
	overrides {
		jquery { 
            defaultBundle 'core' 
        }
	}

//done app
	core {
		dependsOn 'jquery, jquery-ui,carousel,leaflet'
		defaultBundle 'core'

		resource url:'/bootstrap/css/bootstrap.min.css'
		resource url:'/css/bootstrap-combobox.css'
		resource url:'/css/bootstrap-multiselect.css'
		resource url:'/css/auth.css'
//		resource url:[dir:'css',file:'spring-security-ui.css',plugin:'spring-security-ui']
//		resource url:[dir:'css',file:'jquery.safari-checkbox.css',plugin:'spring-security-ui']
//		resource url:'/css/text.css'
		resource url:'/css/navigation.css'
//		resource url:'/css/jquery.rating.css'
		resource url:'/css/daterangepicker.css'
		resource url:'/css/speciesGroups.css'
		resource url:'/css/habitats.css'
		resource url:'/css/tableSorter.css'
		resource url:'/css/bootstrap-editable.css'
		resource url:'/css/biodiv.css'
		resource url:"/css/${org.codehaus.groovy.grails.commons.ConfigurationHolder.config.speciesPortal.app.siteCode}.css"


		resource url:'/bootstrap/js/bootstrap.min.js'
		resource url:'/js/bootstrap-multiselect.js'
		resource url:'/js/species/main.js'
		resource url:'/js/species/util.js'
		resource url:'/js/readmore/readmore.js'
		resource url:'/js/jquery/jquery.form.js'
//		resource url:'/js/jquery/jquery.rating.js'
		resource url:'/js/jquery/jquery.raty.js'
		resource url:'/js/species/rating.js'
		resource url:'/js/jquery/jquery.cookie.js'
		//resource url:'/js/jquery/jquery.checkbox.js'
		resource url:'/js/jquery/jquery.ellipses.js'
		resource url:'/js/species/popuplib.js', disposition: 'head'
		resource url:'/js/species/ajaxLogin.js'
		resource url:'/js/species/users.js'
		resource url:'/js/jquery/jquery.linkify-1.0.js'
		resource url:'/js/timeago.js'
		//resource url:'/js/species/ajaxLogin.js'
		resource url:[dir:'js',file:'jquery/jquery.checkbox.js',plugin:'spring-security-ui']
		resource url:[dir:'js',file:'spring-security-ui.js', plugin:'spring-security-ui']
		resource url:'/js/jquery/trunk8.min.js'
		resource url:'/js/species/membership.js'
		resource url:'/js/date.js'
		resource url:'/js/moment.min.js'
		resource url:'/js/daterangepicker.js'
		resource url:'/js/stats.js'
		resource url:'/js/jquery.tablesorter.js'
		resource url:'/js/bootstrap-editable.js'
		resource url:'/js/species/posting.js'
        resource url:'/js/feature.js'
        resource url:'/js/flag.js'
		resource url:'/js/species/abstracttype.js'
		resource url:'/js/species/abstracteditabletype.js'
		resource url:'/js/species/relatedStory.js'
		resource url:'/js/species/search.js'
	}
//deprecate 
	auth {
		dependsOn 'core'
	}
//deprecate
	gallery {
		//resource url:[dir:'js/galleria/1.3.5/themes/classic/',file:'galleria.classic.css']
		//resource url:'/js/galleria/1.3.5/galleria-1.3.5.js'
		//resource url:'/js/galleria/1.3.5/themes/classic/galleria.classic.min.js'
	}
//done app
	bootstrap_gallery{
		resource url:[dir:'js/bootstrap_gallery/css/',file:'blueimp-gallery.css']
		resource url:'/js/bootstrap_gallery/js/blueimp-helper.js'
		resource url:'/js/bootstrap_gallery/js/blueimp-gallery.min.js'		
		resource url:'/js/bootstrap_gallery/js/blueimp-gallery-fullscreen.js'
		resource url:'/js/bootstrap_gallery/js/blueimp-gallery-indicator.js'
		resource url:'/js/bootstrap_gallery/js/blueimp-gallery-video.js'
		resource url:'/js/bootstrap_gallery/js/blueimp-gallery-vimeo.js'
		resource url:'/js/bootstrap_gallery/js/blueimp-gallery-youtube.js'
		resource url:'/js/bootstrap_gallery/js/jquery.blueimp-gallery.min.js'
	}
//done app
	carousel {
		resource url:[dir:'js/jquery/jquery.jcarousel-0.2.8/themes/classic/',file:'skin.css']
		resource url:'/js/jquery/jquery.jcarousel-0.2.8/jquery.jcarousel.js'
		resource url:'/js/species/carousel.js'
	}
//done app
	tagit {
		resource url:'/css/tagit/jquery.tagit.css'
		resource url:'/css/tagit/tagit-custom.css'
		resource url:'/js/tag-it.js'
	}
//done app
	list_utils {
		resource url:'/js/jquery/jquery-history-1.7.1/scripts/bundled/html4+html5/jquery.history.js'
		resource url:'/js/jquery/jquery.url.js'
//		resource url:[dir:'js/jquery/jquery.jqGrid-4.1.2/css',file:'ui.jqgrid.css']
//		resource url:'/js/jquery/jquery.jqGrid-4.1.2/js/i18n/grid.locale-en.js'
//		resource url:'/js/jquery/jquery.jqGrid-4.1.2/js/jquery.jqGrid.min.js'
		resource url:'/js/jstree-3.1.1/dist/themes/default/style.css'
		resource url:'/js/jstree-3.1.1/dist/jstree.min.js'
		resource url:'/js/jquery/jquery.autopager-1.0.0.js'
        resource url:'/js/species/observations/list.js'
	}

	location_utils { 
        resource url:'/js/location/google/markerclusterer.js'		 
    }
//done app
	observations {
		dependsOn 'core, tagit'
		defaultBundle 'core'

		resource url:'/js/species/names.js'
        resource url:'/css/location_picker.css'
        resource url:'/js/location/location-picker.js'
		resource url:'/js/jquery/jquery.watermark.min.js'
		resource url:'/js/jsrender.js'
		//resource url:'/js/bootstrap-typeahead.js'
		resource url:'/js/bootstrap-combobox.js'
		resource url:'/js/species/observations/map.js'
	}
//done show
	observations_show {
		dependsOn 'observations, gallery, carousel, comment, activityfeed'

		resource url:'/js/species/observations/show.js'
//		resource url:'/js/jquery/jquery.sparkline.min.js'
        resource url:'/js/species/chooseLanguage.js'
	} 
//done create
	observations_create {
		dependsOn 'observations'

		resource url:'/css/create.css'
		resource url:'/js/jquery/jquery.exif.js'
		resource url:'/js/species/observations/addResource.js'
		resource url:'/js/species/observations/create.js'
		resource url:'/js/jquery/jquery.tmpl.min.js'
        resource url:'http://api.filepicker.io/v1/filepicker.js'
        resource url:'/js/species/observations/bulkObvCreate.js'
        resource url:'/js/species/chooseLanguage.js'
	}
//done app
    distinct_reco {
        resource url:'/js/species/observations/distinctReco.js'
    }
//done app
	observations_list { 
		dependsOn 'observations, bootstrap_gallery, list_utils, comment, activityfeed, distinct_reco'
		
		resource url:'/js/species/taxonhierarchy.js'
		resource url:'/js/species/observations/show.js'
		resource url:'/js/species/chooseLanguage.js'
	}
//done app
	susers_list { 
		dependsOn 'core, list_utils'
	
        resource url:'/css/location_picker.css'
        resource url:'/js/location/location-picker.js'
	
		//resource url:'/js/species/observations/list.js'
		//resource url:'/js/species/users/list.js'
	}
//done species
	species {
		dependsOn 'core, list_utils, tagit'

		resource url:'/css/960.css'
		resource url:'/css/main.css'
		resource url:'/css/biodiv.css'
		resource url:'/js/species/taxonhierarchy.js'
		resource url:'/js/species/speciesfield.js'
		resource url:'/js/species/species.js'
	}
//done species_show
	species_show {
		dependsOn 'species, maps, gallery, comment, activityfeed, observations_create'

		resource url:'/css/augmented-maps.css'
		resource url:'/css/bootstrap-wysihtml5-0.0.2.css'
//		resource url:'/css/jquery.tocify.css'
		
//		resource url:'/js/jquery/jquery.tocify.min.js'
		//resource url:'/js/galleria/1.3.5/plugins/flickr/galleria.flickr.min.js'
		//resource url:'/js/jquery.collapser/jquery.collapser.min.js'
//		resource url:'/js/jquery/jquery.jqDock-1.8/jquery.jqDock.min.js'
		resource url:'/js/floating-1.7.js'
		resource url:'/js/wysihtml5-0.3.0_rc2.min.js'
		resource url:'/js/bootstrap-wysihtml5-0.0.2.min.js'		
		resource url:'/js/wysihtml5.js'
        resource url:'/js/species/speciesPermission.js'
        //resource url:'http://malsup.github.com/jquery.form.js'
        
        resource url:'/js/jquery/jquery.autopager-1.0.0.js'
        resource url:'/js/species/observations/list.js'

	}
//done species	
	species_list {
		dependsOn 'observations_list'

        resource url:'/js/species/speciesPermission.js'
	}
//done app
	search {
		dependsOn 'observations_list'

	}
//done app
	admin { dependsOn	'core' }
//done userGroup
	pages {
		resource url:'/js/species/pages.js'
	}
//done show,userGroups	
	userGroups_show {
		dependsOn 'observations, gallery, carousel, activityfeed, pages'

		resource url:'/js/jsrender.js'
		resource url:'/js/species/observations/show.js'
		resource url:'/js/species/userGroups/main.js'
	}
//done userGroup
	userGroups_create {
		dependsOn 'observations'	
		resource url:'/js/species/userGroups/main.js'
	}
//done userGroups
	userGroups_list {
		dependsOn 'observations, location_utils, list_utils'
		
		//resource url:'/js/species/observations/list.js'
		resource url:'/js/species/userGroups/main.js'		
	}
//done
	comment{
		resource url:'/css/comment.css'
		
		resource url:'/js/comment.js'
	}
//done
	activityfeed {
		dependsOn 'core'
		
		resource url:'/css/comment.css'
		resource url:'/css/activityfeed.css'
		
		resource url:'/js/activityfeed.js'
	}
//done slickgrid	
	slickgrid {
		resource url:'/js/SlickGrid-2.0.2/slick.grid.css'
//		resource url:'/js/SlickGrid-2.0.2/css/smoothness/jquery-ui-1.8.16.custom.css'
		resource url:'/js/SlickGrid-2.0.2/examples/examples.css'
		resource url:'/js/SlickGrid-2.0.2/plugins/slick.headerbuttons.css'
		resource url:'/js/SlickGrid-2.0.2/plugins/slick.headermenu.css'


		resource url:'/js/SlickGrid-2.0.2/lib/jquery.event.drag-2.0.min.js'
		resource url:'/js/SlickGrid-2.0.2/slick.core.js'
		resource url:'/js/SlickGrid-2.0.2/slick.formatters.js'
		resource url:'/js/SlickGrid-2.0.2/slick.editors.js'
		resource url:'/js/SlickGrid-2.0.2/slick.grid.js'
		resource url:'/js/SlickGrid-2.0.2/plugins/slick.headerbuttons.js'
		resource url:'/js/SlickGrid-2.0.2/plugins/slick.headermenu.js'
		resource url:'/js/SlickGrid-2.0.2/plugins/slick.cellrangedecorator.js'
		resource url:'/js/SlickGrid-2.0.2/plugins/slick.cellrangeselector.js'
		resource url:'/js/SlickGrid-2.0.2/plugins/slick.cellselectionmodel.js'
	}
//done checklist	
	checklist {
		dependsOn 'location_utils, list_utils, tagit, comment, activityfeed'

		resource url:'/js/bootstrap-rowlink.min.css'
		resource url:'/js/species/checklist.js'
		resource url:'/js/bootstrap-rowlink.min.js'
		resource url:'/js/location/location-picker.js'
		resource url:'/js/species/observations/map.js'
	}
//done checklist	
    checklist_list {
		dependsOn 'checklist'

		//resource url:'/js/species/observations/list.js'
    }
//done bulkupload
	checklist_create {
		dependsOn 'observations_create, checklist, slickgrid, add_file'
		
		resource url:'/js/species/parseUtil.js'
		resource url:'/js/species/jquery.csv-0.71.min.js'
        resource url:'/js/species/observations/upload.js'
	}
//done bulkupload
    species_upload {
        dependsOn 'checklist_create'
    }
//done
	chart {
		dependsOn 'core'
		
		resource url:'/js/chart.js'
	}

    ajaxfileuploader {
        resource url:[plugin: 'ajax-uploader-1.1', dir: 'js', file: 'fileuploader.js']
        resource url:[plugin: 'ajax-uploader-1.1', dir: 'css', file: 'uploader.css']
    }
//done add_file, create.css
	add_file {
		dependsOn 'core, tagit, list_utils, ajaxfileuploader'
		
		resource url:'/css/content.css'
		resource url:'/js/content.js'
		resource url:'/css/location_picker.css'
		resource url:'/js/location/location-picker.js'
	}
//done show.js	
	content_view {
		dependsOn 'core,  tagit'
		resource url:'/css/main.css'
		
		resource url:'/css/content.css'
		resource url:'/js/content.js'
		
	}

    /*prettyPhoto {
        resource url:'/css/prettyPhoto.css'
		resource url:'/js/jquery/jquery.prettyPhoto.js'
    }*/
//done
    leaflet {
        resource url:'js/Leaflet/dist/leaflet.css'
        resource url:'js/Leaflet/dist/leaflet.ie.css', wrapper: { s -> "<!--[if IE]>$s<![endif]-->" }
        resource url:'js/Leaflet/dist/leaflet.js'
        resource url:'js/Leaflet/plugins/leaflet-plugins/layer/tile/Google.js'
        resource url:'js/Leaflet/plugins/Leaflet.Coordinates/dist/Leaflet.Coordinates-0.1.1.css'
        resource url:'js/Leaflet/plugins/Leaflet.Coordinates/dist/Leaflet.Coordinates-0.1.1.min.js'
        resource url:'js/Leaflet/plugins/Leaflet.label/dist/leaflet.label.css'
        resource url:'js/Leaflet/plugins/Leaflet.label/dist/leaflet.label.js'
        resource url:'js/Leaflet/plugins/leaflet-locatecontrol/src/L.Control.Locate.css'
        resource url:'js/Leaflet/plugins/leaflet-locatecontrol/src/L.Control.Locate.ie.css', wrapper: { s -> "<!--[if IE]>$s<![endif]-->" }
        resource url:'js/Leaflet/plugins/leaflet-locatecontrol/src/L.Control.Locate.js'
        resource url:'js/Leaflet/plugins/Leaflet.awesome-markers/dist/leaflet.awesome-markers.css'
        resource url:'js/Leaflet/plugins/Leaflet.awesome-markers/dist/leaflet.awesome-markers.min.js'
        resource url:'js/Leaflet/plugins/leaflet.fullscreen/Control.FullScreen.js'
        resource url:'js/Leaflet/plugins/leaflet.fullscreen/Control.FullScreen.css'
        resource url:'js/Leaflet/plugins/Leaflet.draw/dist/leaflet.draw.css'
        resource url:'js/Leaflet/plugins/Leaflet.draw/dist/leaflet.draw.ie.css', wrapper: { s -> "<!--[if IE]>$s<![endif]-->" }
        resource url:'js/Leaflet/plugins/Leaflet.draw/dist/leaflet.draw.js'
        resource url:'js/Wicket/wicket.js'
        resource url:'js/Wicket/wicket-leaflet.js'
        resource url:'js/Leaflet/plugins/Leaflet.markercluster/dist/leaflet.markercluster-src.js'
        resource url:'js/Leaflet/plugins/Leaflet.markercluster/dist/MarkerCluster.css'
        resource url:'js/Leaflet/plugins/Leaflet.markercluster/dist/MarkerCluster.Default.css'
        resource url:'js/Leaflet/plugins/Leaflet.markercluster/dist/MarkerCluster.Default.ie.css', wrapper: { s -> "<!--[if IE]>$s<![endif]-->" }
 
    }
//done in maps.css maps.js
    maps {
    	dependsOn 'core'
		
		resource url:'/css/am.css'
		resource url:'/css/styles.css'
        resource url:'/js/OpenLayers-2.10/theme/default/style.css'

		resource url:'/js/OpenLayers-2.10/OpenLayers.js'
		resource url:'/js/species/maps/am.js'
		resource url:'/js/species/maps/map-search.js'
		resource url:'/js/species/maps/mapapp.js'
		resource url:'/js/species/maps/cookie-chef.js'
    }
//done curation.js
    curation {
		dependsOn 'comment, activityfeed'

		resource url:'/js/species/taxonhierarchy.js'
        resource url:'/js/species/speciesPermission.js'
        resource url:'/js/species/curation.js'
        resource url:'/css/namelist.css'
    }
//done app
    document_list {
        dependsOn 'observations_list'
    }
}
