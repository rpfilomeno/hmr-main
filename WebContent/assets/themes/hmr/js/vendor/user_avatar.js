jQuery(document).ready(function($) {
	
	var base_url = $('#base_url').val()
	,	$dropElement = $('#media-drop-element')
	,	max_file_count = 1
	,	currentURL = new Url
	,	userAvatar;
	
	var $userPicWrapper = $('#current_pic');
	var $preloader = $('#avatar-preloader');
	var $pickfiles = $('#pickfiles');

	var uploader = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',
		browse_button : 'pickfiles', // you can pass an id...
		// drop_element: 'media-drop-element',
		// dragdrop: true,
		container: document.getElementById('pickfiles-container'), // ... or DOM Element itself
		url : base_url + 'cms/media/do_upload',
		flash_swf_url : base_url + 'js/vendor/Moxie.swf',
		silverlight_xap_url : base_url + 'js/vendor/Moxie.xap',
		filters : {
			max_file_size : ($dropElement.data('max-file-size')) ? $dropElement.data('max-file-size') : '100mb',
			mime_types: [
				{title : "Image files", extensions : "jpg,jpeg,gif,png"},
			]
		},
		multi_selection: false,
		// chunks: true,
		multipart_params : {
	        "save_avatar" : 1,
	        "avatar_for" : $pickfiles.data('avatar-for'),
	        "attach_id" : currentURL.query.id,
	        "attachment_for" : "avatar",
	    }
	});
	// console.log(uploader);

	uploader.init();

	

	// PostInit
	uploader.bind('PostInit', function () {
		
		// $('#filelist').html('')
		// $('#uploadfiles').on('click', function (e) {
		// 	console.log("do upload")
		// 	uploader.start();
		// 	return false;
		// });
	});

	// UploadProgress
	uploader.bind('UploadProgress', function (up, file) {
		$('#'+file.id).find('.progress-bar').css('width', file.percent + '%').find('.sr-only').text(file.percent + '%');
		if ( file.percent == 100 ) {
			setTimeout(function () {
				$preloader.hide();
			}, 500)
		};
	});

	// FilesAdded
	uploader.bind('FilesAdded', function (up, files) {
	    console.log(files)
		
		if(up.files.length > max_file_count){
	        alert("Max File Count is " + max_file_count + '\n' + 'Upload ' + max_file_count + ' files at a time.');
	        for(var i in files){
				uploader.removeFile(files[i]);
			};
	        return;
	    }

		for(var i in files){
			var file = files[i];
			$preloader.prepend('<div id="'+ file.id +'"><div class="media-preview file-detail files-added"><span class="file_name">'+ file.name + '</span> <span class="file_size">('+ plupload.formatSize(file.size) +')</span>'+ '<div class="progress"><div class="progress-bar progress-bar-striped active" style="width: 20%" role="progressbar"><span class="sr-only"></span></div></div></div></div>');
		};
		uploader.start();
		uploader.refresh();
	});


	// Error
	uploader.bind('Error', function (up, err) {
		// console.log(up)
		console.log(err.response)
		if ( $('#'+ err.file.id).length !=0 ) {
			$preloader.addClass('error-file').html('<div class="media-preview file-detail"><span class="file_name">'+ err.file.name + '</span> <span class="file_size">('+ plupload.formatSize(err.file.size) +')</span>' + '<div class="text-danger">'+ err.message +'</div>' +'</div>');
		}else{
			$preloader.prepend('<div id="'+ err.file.id +'" class="error-file"><div class="media-preview file-detail"><span class="file_name">'+ err.file.name + '</span> <span class="file_size">('+ plupload.formatSize(err.file.size) +')</span>' + '<div class="text-danger">'+ err.message +'</div>' +'</div></div>');
		}
		uploader.refresh();
	});

	uploader.bind('FileUploaded', function (up, file, response) {
		var jsonResponse = $.parseJSON(response.response);

		console.log(jsonResponse)
		if ( jsonResponse.is_valid ) {
			$('.msis-error-message-wrapper').hide();
			$pickfiles.text('Change Picture');

			$userPicWrapper.html( '<img class="img-thumbnail" src="'+ base_url + jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name  +'">' );

			if ( typeof jsonResponse.upload_data.avatar_crop_preview == "undefined") return;

			var $cropModal = bootbox.dialog({
				title: "Cropping",
				message: '<div id="cropping-area" class="tac">'+
							'<img id="avatar-for-crop" src="'+ jsonResponse.upload_data.avatar_crop_preview  +'"></div>'+
							'<input type="hidden" id="jcrop-path" name="path" value="'+ jsonResponse.upload_data.upload_full_path +'" />' +
							'<input type="hidden" id="jcrop-filename" name="filename" value="'+ jsonResponse.upload_data.file_name +'" />' +
							'<input type="hidden" id="jcrop-raw-name" name="raw-name" value="'+ jsonResponse.upload_data.raw_name +'" />' +
							'<input type="hidden" id="jcrop-ext" name="ext" value="'+ jsonResponse.upload_data.file_ext +'" />' +
							'<input type="hidden" id="jcrop-x" name="x" />' +
							'<input type="hidden" id="jcrop-y" name="y" />' +
							'<input type="hidden" id="jcrop-w" name="w" />' +
							'<input type="hidden" id="jcrop-h" name="h" />',
				buttons: {
				    save: {
				      label: "Save",
				      className: "btn-sm btn-success",
				      callback: function() {
				      	userAvatar.saveCrop();
				        // msMedia.quickSave();
				        return false;
				      }
				    }
				}
			});

			$cropModal.on("shown.bs.modal", function() {
			  userAvatar.init();
			});

		};

		if ( !jsonResponse.is_valid ) {
			$pickfiles.text('Select file');
			$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail"><span class="file_name">'+ file.name + '</span> <span class="file_size">('+ plupload.formatSize(file.size) +')</span>' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
			uploader.refresh();
		};

	});
	

	userAvatar = {
		init: function () {
			this.bindEvents();
		},
		
		bindEvents: function () {
			userAvatar.cropper();


		},

		saveCrop: function () {
			
			$.ajax({
				url: base_url + 'cms/media/crop_avatar',
				type: 'POST',
				dataType: 'json',
				data: {
					user_id: currentURL.query.id,
					path: $('#jcrop-path').val(),
					filename: $('#jcrop-filename').val(),
					raw_name: $('#jcrop-raw-name').val(),
					ext: $('#jcrop-ext').val(),
					x: $('#jcrop-x').val(),
					y: $('#jcrop-y').val(),
					w: $('#jcrop-w').val(),
					h: $('#jcrop-h').val(),
				},
			})					
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				if ( data.is_valid ) {
					bootbox.hideAll();
					$userPicWrapper.html( '<img class="img-thumbnail" src="'+ data.avatar +'">' );

					var avatarBtns = {
						dismiss: {
					      label: "Dismiss",
					      className: "btn-sm btn-default",
					      callback: function () {
					      	if ( $pickfiles.data('avatar-for') == "user_avatar" ) {
					      		window.location = base_url + 'cms/users/profile/' + data.id;
					      	}else if ( $pickfiles.data('avatar-for') == "employee_avatar" ) {
					      		window.location = base_url + 'cms/employees/profile/' + data.id;
					      	};
					      }
					    }
					};
					if ( $pickfiles.data('avatar-for') == "user_avatar" ) {
						avatarBtns.save = {
					      label: "Set account details",
					      className: "btn-sm btn-primary",
					      callback: function() {
					        window.location = base_url + 'cms/users/edit?id=' + data.id + '&step=3';
					        return false;
					      }
					    };
					};

					bootbox.dialog({
						title: "Congratulations!",
						message: "Profile Picture has been saved.",
						buttons: avatarBtns
					})
				};
				console.log("complete");
			});

		},

		cropper: function () {
			$('#avatar-for-crop').Jcrop({
			    minSize: [160, 160],
			    setSelect: [ 80, 80, 100, 100 ],
			    aspectRatio: 1,
			    bgFade: true,
			    onSelect: userAvatar.updateCoords,
			    onChange: userAvatar.updateCoords
			});
		},

		updateCoords: function(c){
		    $('#jcrop-x').val(c.x);
		    $('#jcrop-y').val(c.y);
		    $('#jcrop-w').val(c.w);
		    $('#jcrop-h').val(c.h);
		},


	};

	userAvatar.init();
	

});