// Utility
if ( typeof Object.create !== 'function' ) {
	Object.create = function ( obj ) {
		function F () {};
		F.prototype = obj;
		return new F();
	}
};

(function ($, window, document, undefined) {
	var kUploader = {
		init: function ( options, elem ) {
			var self = this;

			self.elem = elem;
			self.$elem = $( elem );

			self.options = $.extend( {}, $.fn.kUploader.options, options );

			self.renderBaseTpl();
			$('#'+self.options.containerWrapID).find('.progress').hide();

			if ( self.options.deleteURL !=null ) {
				self.deleteBtn();
			};

			self.openByBtn();
			self.doUpload();
		},
		openByBtn: function () {
			var self = this
			,	fileSelect = self.$elem.find('input:file');
			
			fileSelect.hide();
			$('#'+self.options.containerWrapID).find('.'+self.options.uploadBtnClass).live('click', function () {
				fileSelect.click();
			});
		},
		deleteBtn: function () {
			var self = this;
			$('.kupload-delete-btn').live('click', function (e) {
				var $this = $(this);

		        if ( !confirm( "Are you sure you want to delete "+ $this.data('orig-name') +"?" ) ){ return false; }

				$.ajax({
					url: self.options.deleteURL,
					type: 'POST',
					dataType: 'json',
					data: { 
						filename: $this.data('filename'),
						path: $this.data('path'),
						is_image: $this.data('is-image')
					},
				})
				.done(function() {
					console.log("success");
				})
				.fail(function(e) {
					console.log(e.responseText);
					console.log("error");
				})
				.always(function(data) {
					console.log(data)
					if ( data.is_valid ) {

						if ( typeof $.files_uploaded != 'undefined' ) {
							$.files_uploaded = $.grep($.files_uploaded, function(data, index) {
							   return data.file_name != $this.data('filename');
							});
						};

						webArtJs.messenger({
							message: $this.data('orig-name') + ' has been deleted!',
							closeAfter: 3000,
							position: "small bottom-left"
						});

						$this.parents('.klist-wrap').addClass('animated').addClass('hinge');
						setTimeout(function () {
							$this.parents('.klist-wrap').remove()
						}, 2000);
					};
					console.log("complete");
				});
				
				e.preventDefault();
			});
		},
		doUpload: function () {
			var self = this;
			
			self.$elem.find('input:file').live('change', function () {
				
			if ( typeof self.options.onDoUpload === 'function' ) {
				self.options.onDoUpload.apply( self.elem, arguments );
			};

				self.$elem.ajaxForm({
					dataType:  'json', 
					beforeSend: function() {

						if ( typeof self.options.beforeSend === 'function' ) {
							self.options.beforeSend.apply( self.elem, arguments );
						};

						$('.'+self.options.uploadBtnClass).prop('disabled', true);
						$('#'+self.options.containerWrapID).find('.progress').show();
						var percentVal = '0%';
						$('#'+self.options.containerWrapID + ' .progress .progress-bar').removeClass('active');
						$('#'+self.options.containerWrapID + ' .progress .progress-bar').attr('aria-valuenow', 0);
						$('#'+self.options.containerWrapID + ' .progress .progress-bar').width(percentVal);
						$('#'+self.options.containerWrapID + ' .progress .progress-bar').html(percentVal);
					},
					uploadProgress: function(event, position, total, percentComplete) {

						if ( typeof self.options.uploadProgress === 'function' ) {
							self.options.uploadProgress.apply( self.elem, arguments );
						};
						var percentVal = percentComplete + '%';
						$('#'+self.options.containerWrapID).find('.progress').show();
						$('#'+self.options.containerWrapID + ' .progress .progress-bar').addClass('active');
						$('#'+self.options.containerWrapID + ' .progress .progress-bar').attr('aria-valuenow', percentComplete);
						$('#'+self.options.containerWrapID + ' .progress .progress-bar').width(percentVal);
						$('#'+self.options.containerWrapID + ' .progress .progress-bar').html(percentVal);
					},
					success: function(e) {
						if ( typeof self.options.success === 'function' ) {
							self.options.success.apply( self.elem, arguments );
						};

						if (e.is_valid) {
							$.files_uploaded = e.files_uploaded;
							console.log( $.files_uploaded );
							if ( self.options.doDisplayList ) {
								self.display(e.files_uploaded);
							};

							console.log("all files are being save to $.files_uploaded");
						}else{
							console.log("There are error while uploading...");
						}
						
						$('.'+self.options.uploadBtnClass).prop('disabled', false);
						var percentVal = '100%';
						$('#'+self.options.containerWrapID + ' .progress .progess-bar').addClass('active');
						$('#'+self.options.containerWrapID + ' .progress .progess-bar').attr('aria-valuenow', 100);
						$('#'+self.options.containerWrapID + ' .progress .progress-bar').width(percentVal);
						$('#'+self.options.containerWrapID + ' .progress .progess-bar').html(percentVal);
						
						$('#'+self.options.containerWrapID).find('.progress').hide();
						// console.log(e)
					},
					error: function (e) {
						if ( typeof self.options.onFail === 'function' ) {
							self.options.onFail.apply( self.elem, arguments );
						};
						$('.'+self.options.uploadBtnClass).prop('disabled', false);
						console.log(e.responseText);
						console.log('error');
						$('#'+self.options.containerWrapID).find('.progress').hide();
					}
		      }).submit();

			});
		},

		display: function (files) {
			var self = this;
			var path = self.options.uploadPath;

			$.each(files, function(index, val) {
				var content = ( val.is_image ) ? '<img src="'+path + '/' + 'thumb_' + val.file_name+'" alt="'+ val.orig_name +'" />' : ''
				var is_image = ( !val.is_image ) ? 'active' : '';
				$('#kuploader-list-wrap')
				.append('<span class="klist-wrap animated '+ is_image +' ">'+
							'<a target="_BLANK" class="klist" href="'+ path + '/' + val.file_name +'">'+
								'<div class="klist-img-wrap">'+ content +'</div>'+
							'</a>' + 
						'<div class="klist-label-wrap">'+
							'<div class="klist-type-icon">'+
								self.options.fileSymbol+
							'</div>'+
							'<div class="klist-label">'+
								'<span class="k-label">'+ val.orig_name +
									'<div class="fsize">'+val.file_size+' kb</div>'+
								'</span>'+
							'</div> '+
						'</div>'+
						'<div class="klist-option">'+
							'<a rel="tooltip" data-placement="top" title="Download" target="_BLANK" class="btn btn-primary" href="'+ path + '/' + val.file_name +'">'+
								self.options.downloadBtnText+
							'</a><a rel="tooltip" data-placement="top" title="Delete" class="btn btn-primary kupload-delete-btn" data-is-image="'+val.is_image+'" data-path="'+path+'" data-filename="'+val.file_name+'" data-orig-name="'+val.orig_name+'" href="#">'+ self.options.deleteBtnText +'</a> </div> </span>');
			
			// refresh bootstrap tooltip
			$('[rel="tooltip"]').tooltip();
			});
			
		},

		limit: function ( obj, count ) {
			
		},

		renderBaseTpl: function () {
			self = this;
			var baseTpl = [];
			baseTpl = ['<div id="', self.options.containerWrapID ,'"> <div class="progress">',
					   '<div class="progress-bar progress-bar-striped  progress-bar-wa"  role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">',
					   '</div></div> <div id="kuploader-list-wrap"></div><div class="clearfix"></div><button class="btn btn-default btn-mini ', self.options.uploadBtnClass ,'">', self.options.btnText ,'</button></div>'];

			$(baseTpl.join('')).insertBefore(self.$elem);
		},

	};

	$.fn.kUploader = function ( options ) {
		return this.each(function() {

			var kUpload = Object.create(  kUploader );
			kUpload.init( options, this);

			$.data( this, 'kUploader', kUpload );
		});
	};
 
	$.fn.kUploader.options = {
		// table: 'users',
		template : '<li></li>',
		beforeSend: null,
		uploadProgress: null,
		success: null,
		onFail: null,
		dataType: 'json',
		containerWrapID: 'kUploader-wrapper',
		uploadBtnClass: 'upload-btn',
		uploadPath: null,
		deleteURL: null,
		btnText: '<span class="glyphicon glyphicon-paperclip"></span>Attach Files',
		doDisplayList: true,
		downloadBtnText: 'Download',
		deleteBtnText: 'Delete',
		fileSymbol: '',
	};

})( jQuery, window, document );