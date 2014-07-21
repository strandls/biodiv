function removeResource(event, imageId) {
    var targ;
    if (!event) var event = window.event;
    if (event.target) targ = event.target;
    else if (event.srcElement) targ = event.srcElement; //for IE
    else {}
    $(targ).parent('.addedResource').remove();
    $(targ).find(".image_"+imageId).remove();
    if($( "input[name='resType']" ).val() == "species.auth.SUser") {
        $(".image_"+imageId).first().closest(".addedResource").css('opacity', '1');
        $(".image_"+imageId).first().closest(".addedResource").draggable('enable');
    }

}

/**
  @class uploadResource
 **/
(function ($) {
    "use strict";

    var UploadResource = function (ele, options) {
        this.$ele = $(ele);
        this.$form = $(ele).find('form#upload_resource');
        this.initForm(options);
    }

    UploadResource.prototype = {

        initForm : function(options) {
            var me = this;
            me.$ele.find('.add_image').bind('click', $.proxy(me.filePick, me));
            me.$ele.find('.add_audio').bind('click', $.proxy(me.filePickAudio, me));

            var videoOptions = {
                type : 'text',
                mode : 'popup',
                emptytext : '',
                placement : 'bottom', 
                url : function(params) {
                    var d = new $.Deferred;
                    if(!params.value) {
                        return d.reject('This field is required'); //returning error via deferred object
                    } else {
                        me.$form.find('.videoUrl').val(params.value);
                        me.submitRes();
                        d.resolve();
                    }
                    return d.promise() 
                }, 
                    validate :  function(value) {
                        if($.trim(value) == '') {
                            return 'This field is required';
                        }
                    }, 
                title : 'Enter YouTube watch url like http://www.youtube.com/watch?v=v8HVWDrGr6o'
            };



            var audioOptions = {
                type : 'text',
                mode : 'popup',
                emptytext : '',
                placement : 'bottom', 
                url : function(params) {
                    var d = new $.Deferred;
                    if(!params.value) {
                        return d.reject('This field is required'); //returning error via deferred object
                    } else {
                        me.$form.find('.audioUrl').val(params.value);
                        me.submitRes();
                        d.resolve();
                    }
                    return d.promise() 
                }, 
                    validate :  function(value) {
                        if($.trim(value) == '') {
                            return 'This field is required';
                        }
                    }, 
                title : 'Enter Audio url like http://test.com/sample.mp3'
            };


            $.extend( videoOptions, options);
            me.$ele.find('.add_video').editable(videoOptions);
           // me.$ele.find('.add_audio').editable(audioOptions);





            me.$form.ajaxForm({ 
                url:window.params.observation.uploadUrl,
                dataType: 'xml',//could not parse json wih this form plugin 
                clearForm: true,
                resetForm: true,
                type: 'POST',
                beforeSubmit: function(formData, jqForm, opts) {
                    me.$ele.find("#addObservationSubmit").addClass('disabled');
                    return true;
                }, 
                context:me,
                success : me.onUploadResourceSuccess,
                error : me.onUploadResourceError
            });
        },

        submitRes : function() {
            this.$form.submit().find("span.msg").html("Uploading... Please wait...");
            this.$ele.find(".iemsg").html("Uploading... Please wait...");
            this.$ele.find(".progress").css('z-index',110);
            this.$ele.find('.progress_msg').html('Uploading ...');
        },

        filePick : function(e) {
            var me = this;
            var onSuccess = function(FPFiles){
                var count = 0;
                $.each(FPFiles, function(){
                    $('<input>').attr({
                        type: 'hidden',
                        name: 'resources',
                        value:JSON.stringify(this)
                    }).appendTo(me.$form);
                    count = count + 1;
                });
                if($( "input[name='resType']" ).val() == "species.auth.SUser") {
                    $("input[name='obvDir']").val('');
                    $('<input>').attr({
                        type: 'hidden',
                        name: 'lastUploaded',
                        value: count
                    }).appendTo(me.$form);
                }    
                me.submitRes();
            };

            var filepickerOptions = {
                maxSize: 104857600,
                services:['COMPUTER', 
                'FACEBOOK', 
                'FLICKR', 
                'PICASA', 
                'GOOGLE_DRIVE', 
                'DROPBOX'],
                mimetypes: ['image/*']
            };
            try {
            filepicker.pickMultiple(filepickerOptions, onSuccess, function(FPError){ 
                console.log(FPError.toString());
            });
            } catch(e) {
                console.log('filepicker error : '+e);
            }
                                    
        },






         filePickAudio : function(e) {
            var me = this;
            var onSuccess = function(FPFiles){
                $.each(FPFiles, function(){
                    $('<input>').attr({
                        type: 'hidden',
                        name: 'resources',
                        value:JSON.stringify(this)
                    }).appendTo(me.$form);
                });
                me.submitRes();
            };

            var filepickerOptions1 = {
                maxSize: 104857600,
                services:['COMPUTER', 
                //'FACEBOOK',                 
                'GOOGLE_DRIVE', 
                'DROPBOX'],
                mimetypes: ['audio/*']
            };
            try {
            filepicker.pickMultiple(filepickerOptions1, onSuccess, function(FPError){ 
                console.log(FPError.toString());
            });
            } catch(e) {
                console.log('filepicker error : '+e);
            }
                                    
        },





        onUploadResourceSuccess : function(responseXML, statusText, xhr, form) {
            var me = this;
            me.$ele.find("#addObservationSubmit").removeClass('disabled');
            $(form).find("span.msg").html("");
            me.$ele.find(".progress").css('z-index',90);
            me.$ele.find('.progress_msg').html('');
            me.$ele.find(".iemsg").html("");
            //var rootDir = '${grailsApplication.config.speciesPortal.observations.serverURL}'
            //var rootDir = '${Utils.getDomainServerUrlWithContext(request)}' + '/observations'
            var obvDir = $(responseXML).find('dir').text();
            var obvDirInput = me.$form.find('input[name="obvDir"]');
            if(!obvDirInput.val()){
                $(obvDirInput).val(obvDir);
            }
            var images = [];
            var metadata = me.$ele.find(".metadata");
            var i = 0;
            if(metadata.length > 0) {
                var file_id = $(metadata.get(0)).children("input").first().attr("name");
                i = parseInt(file_id.substring(file_id.indexOf("_")+1));
            }
            var $s = $(responseXML).find('resources').find('res');
            var x = $s.length;
            $s.each(function() {
                var fileName = $(this).attr('fileName');
                var type = $(this).attr('type');					
                images.push({i:x+i, file:fileName, url:$(this).attr('url'), thumbnail:$(this).attr('thumbnail'), type:type, title:fileName});
                x--;
            });
            var html = $( "#metadataTmpl" ).render( images );
            var metadataEle = $(html);
            if($( "input[name='resType']" ).val() == "species.participation.Observation") {
                metadataEle.each(function() {
                    me.$ele.find(".address").trigger('click'); 
                    /*$('.geotagged_image', this).load(function(){
                        $(".map_class").data('locationpicker').mapLocationPicker.update_geotagged_images_list($(this));		
                    });*/
                    var $ratingContainer = $(this).find('.star_obvcreate');
                    rate($ratingContainer);
                });
            }
            me.$ele.find(".imagesList li:first" ).after (metadataEle);
            me.$ele.find(".add_file" ).fadeIn(3000);
            me.$ele.find(".image-resources-msg").parent(".resources").removeClass("error");
            me.$ele.find(".image-resources-msg").html("");
            me.$form.find("input[name='resources']").remove();
            me.$ele.find('.videoUrl').val('');
            me.$ele.find('.audioUrl').val('');
            me.$ele.find('.add_video').editable('setValue','', false);
           // me.$ele.find('.add_audio').editable('setValue','', false);		
            me.$ele.find('.add_video').editable('setValue','', false);	
            
            if($( "input[name='resType']" ).val() == "species.auth.SUser") {
                /*
                if($(".imagesList li").size() > 4){
                    console.log("=============INCREASING WIDTH===========");
                    var w = $(".imagesList").css("width");
                    var w = w + 150;
                    $(".imagesList").css("width", w);
                }
                */
                var count = $("input[name='lastUploaded']").val();
                var metadataForForm = $(".metadata.prop:lt("+count+")").clone();
                $(metadataForForm).css("display","none");
                $("form.createResource").find(".metadata.prop").remove();
                $(metadataForForm).appendTo($("form.createResource"));

                $("form.createResource").ajaxSubmit({
                url : $(this).attr("action"),
                dataType : 'json', 
                type : 'POST',
                success : function(data, statusText, xhr, form) {
                    $(".addedResource.thumbnail").draggable({helper:'clone'});  

                    $(".imageHolder").droppable({
                        accept: ".addedResource.thumbnail",
                        drop: function(event,ui){
                            dropAction(event, ui, this);    
                        }
                    });
                }, error : function (xhr, ajaxOptions, thrownError){

                }  
                });
                $("input[name='obvDir']").val('');
            }

            },

                onUploadResourceError : function (xhr, ajaxOptions, thrownError) {
                    var successHandler = this.success, errorHandler;
            var me = this;
            handleError(xhr, ajaxOptions, thrownError, successHandler, function(data) {
                if(data && data.status == 401) {
                    me.submitRes();
                    return; 
                }
                me.$ele.find("#addObservationSubmit").removeClass('disabled');                
                me.$form.find("input[name='resources']").remove();
                me.$ele.find('.videoUrl').val('');
                me.$ele.find('.audioUrl').val('');
                me.$ele.find(".progress").css('z-index',90);
                me.$ele.find('.add_video').editable('setValue','', false);
              //  me.$ele.find('.add_audio').editable('setValue','', false);
                //xhr.upload.removeEventListener( 'progress', progressHandlingFunction, false); 

                var response = $.parseJSON(xhr.responseText);               
                if(response.error){
                    alert(response.error);
                    me.$ele.find(".image-resources-msg").parent(".resources").addClass("error");
                    me.$ele.find(".image-resources-msg").html(response.error);
                }

                var messageNode = me.$ele.find(".message .resources");
                if(messageNode.length == 0 ) {
                    me.$form.prepend('<div class="message">'+(response?response.error:"Error")+'</div>');
                } else {
                    messageNode.append(response?response.error:"Error");
                }

            });
        } 
    }

    //making object visible outside
    $.fn.components.UploadResource = UploadResource;

}(window.jQuery)); 



$(document).ready(function(){
    $('.dropdown-toggle').dropdown();

    /**
     * upload_resource & FilePicker
     */
    /*
       function filePick() {
       var onSuccess = function(FPFiles){
       $.each(FPFiles, function(){
       $('<input>').attr({
       type: 'hidden',
       name: 'resources',
       value:JSON.stringify(this)
       }).appendTo('.upload_resource');
       });
       uploadResource.submit();
       };
       filepicker.pickMultiple({
       maxSize: 104857600,
       services:['COMPUTER', 'FACEBOOK', 'FLICKR', 'PICASA', 'GOOGLE_DRIVE', 'DROPBOX'],
       mimetypes: ['image/*'] }, onSuccess, function(FPError){ console.log(FPError.toString()); });

       }
       */

    function newPicker() {
        google.load('picker', '1', {"callback" : createPicker});
    }

    // Create and render a Picker object for searching images.
    var createPicker = function () {
        var picker = new google.picker.PickerBuilder().
            addView(google.picker.ViewId.YOUTUBE).
            setCallback(pickerCallback).
            build();
        picker.setVisible(true);
        //$(".picker-dialog-content").prepend("<div id='anyVideoUrl' class='editable'></div>");
        //$('#anyVideoUrl').editable(addVideoOptions);
    }

    // A simple callback implementation.
    function pickerCallback(data) {
        var url = 'nothing';
        if (data[google.picker.Response.ACTION] == google.picker.Action.PICKED) {
            var doc = data[google.picker.Response.DOCUMENTS][0];
            url = doc[google.picker.Document.URL];
            if(url) {
                $('#videoUrl').val(url);
                uploadResource.submitRes();
            }
        }
    }



    /*
       var onVideoAddSuccess = function(params) {
       var d = new $.Deferred;
       if(!params.value) {
       return d.reject('This field is required'); //returning error via deferred object
       } else {
       $('#videoUrl').val(params.value);
       uploadResource.submit();
       d.resolve();
       }
       return d.promise()  
       }

       var onVideoAddValidate = function(value) {
       if($.trim(value) == '') {
       return 'This field is required';
       }
       }
       */
    //$('#add_video').editable(addVideoOptions);
    $.each($('.star_obvcreate'), function(index, value){
        rate($(value));
    });


    /**
    */
    $('#attachFiles').change(function(e){
        uploadResource.submitRes();
    });


});


