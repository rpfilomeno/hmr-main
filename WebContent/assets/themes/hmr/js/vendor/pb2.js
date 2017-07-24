
var MSPB2;

jQuery(document).ready(function($) {
	
	var base_url = $('#base_url').val()
	,	tinymceData = {
			currentParentWrap: ''
	}
	, 	is_query = 0
	, 	getFormQuery = 0
	,	tinymceCount = 0
	,	currentURL = new Url;

	MSPB2 = {
		init: function () {
			this.bindEvents()
			// this.contentTiny()
			this.contentTools()
			this.saveContent()
		},
		bindEvents: function () {

			// $('#main-navigation a').on('click', function (e) {
			// 	e.preventDefault();
			// });

			// $(".btn-cancel-content").live('click', function(){
			//    alert("test from pb2");
			// });

			this.delBlock();
			this.editBlock();
			this.resizeBlock();
			this.resizeSpacer();
		},

		resizeBlock: function () {

			var gridSize = {col12: 100, col11: 91.66666667, col10: 83.33333333, col9: 75, col8: 66.66666667, col7: 58.33333333, col6: 50, col5: 41.66666667, col4: 33.33333333, col3: 25, col2: 16.66666667, col1: 8.33333333};

			$.each($('.content-block'), function(index, val) {
				var $this = $(this);
				
				if ( !$this.hasClass('resize-loaded') && $this.data('type') != "Spacer" ) {


					$('#'+ $this.attr('id')).resizable({
				    	grid: ($this.data('type') == "Spacer") ? [50, 0] : 50,
				    	handles: "e",
				    	resize: function (event, ui) {
				    	
				    		var originalSize = ui.originalSize;
				    		var $baseWidth = $('.builder-container').width();
				    		var curWidth = ui.size.width;
				    		var media = 'md';
				    		var percent = ($baseWidth - curWidth) / $baseWidth * 100;
				    		var percentInverse = 100 - percent;

				    		var curClass = '';

				    		var i = 0;
				    		var ii = 1;

				    		console.log( percentInverse )

				    		if ( percentInverse == 100 ) {
				    			curClass = 'col-'+media+'-12';
				    		}else{
				    			$.each(gridSize, function(index, val) {
					    			i++; ii++;
					    			if ( percentInverse > gridSize['col'+i] && typeof gridSize['col'+ii] != "undefined" && percentInverse < gridSize['col'+ii] ) {
							   			curClass = 'col-'+media+'-'+ i;
							   		};

					    		});
				    		}

				    		ui.element.data('wrap-class', curClass);
				    		ui.element.parents('.content-block-col').removeAttr('class').addClass(curClass).addClass('content-block-col');
				    		console.log(curClass)

				    		ui.element.removeAttr('style')
				    		if ( $this.data('type') =="Spacer" ) {
				    			ui.element.addClass('active');
				    			ui.element.find('.spacer-block-content').css({'height': ui.size.height, 'min-height': ui.size.height});
				    		};

				    	},
				    	stop: function (event, ui) {
				    			    		
				    		ui.element.removeAttr('style')
				    		if ( $this.data('type') =="Spacer" ) {
				    			ui.element.removeClass('active');
				    			ui.element.find('.spacer-block-content').css({'height': ui.size.height, 'min-height': ui.size.height});
				    		};
				    	}
				    }).addClass('resize-loaded');	
				}

			});
		},

		resizeSpacer: function () {
		
			$.each($('.spacer-block'), function(index, val) {
				var $this = $(this);

				if ( !$this.hasClass('resize-loaded') ) {

					$('#'+ $this.attr('id')).resizable({
				    	grid: [50, 0],
				    	handles: "n, s",
				    	resize: function (event, ui) {
				    	
				    		var originalSize = ui.originalSize;
				    		// var $baseWidth = $('.builder-container').width();
				    		// var curWidth = ui.size.width;
				    		// var media = 'xs';
				    		// var percent = ($baseWidth - curWidth) / $baseWidth * 100;
				    		// var percentInverse = 100 - percent;

				    		// var curClass = '';

				    		// var i = 0;
				    		// var ii = 1;

				    		// console.log( percentInverse )

				    		// if ( percentInverse == 100 ) {
				    		// 	curClass = 'col-'+media+'-12';
				    		// }else{
				    		// 	$.each(gridSize, function(index, val) {
					    	// 		i++; ii++;
					    	// 		if ( percentInverse > gridSize['col'+i] && typeof gridSize['col'+ii] != "undefined" && percentInverse < gridSize['col'+ii] ) {
							   // 			curClass = 'col-'+media+'-'+ i;
							   // 		};

					    	// 	});
				    		// }

				    		// ui.element.data('wrap-class', curClass);
				    		// ui.element.parents('.content-block-col').removeAttr('class').addClass(curClass).addClass('content-block-col');
				    		// console.log(curClass)

				    		ui.element.removeAttr('style')
				    		
			    			ui.element.addClass('active');
			    			ui.element.find('.spacer-block-content').css({'height': ui.size.height, 'min-height': ui.size.height});

				    		console.log(ui.size.height)

				    	},
				    	stop: function (event, ui) {
				    		console.log(ui.size.height)
				    		// ui.element.removeAttr('style')
				    		// if ( $this.data('type') =="Spacer" ) {
				    		// 	ui.element.removeClass('active');
				    		// 	ui.element.find('.spacer-block-content').css({'height': ui.size.height, 'min-height': ui.size.height});
				    		// };
				    	}
				    }).addClass('resize-loaded');	
				}
			});

		},

		editBlock: function () {
			
			$('.edit-block').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);
				var blockType = $this.parents('.content-block').data('type');
				var blockID = $this.data('id');
				var mediaID = $this.data('media-id');

				$('.active-block').removeClass('active-block');

				$this.parents('.content-block').addClass('active-block');

				if ( blockType == "Paragraph" ) {
					MSPB2.contentTiny( '#' + $this.data('id'), true );
				};

				if ( blockType == "Quote" ) {
					MSPB2.quoteBlock(blockID);
				};

				if ( blockType == "Image" ) {
					MSPB2.imageDialog(blockID, mediaID);
				};

				if ( blockType == "Video" ) {
					MSPB2.videoDialog(blockID, mediaID);
				};

				if ( blockType == "Embed" ) {
					MSPB2.embedDialog(blockID);
				};

				if ( blockType == "Summary Wall" || blockType == "Summary Carousel" || blockType == "Summary List" || blockType == "Summary Grid" ) {
					MSPB2.summaryDialog(blockID, blockType);
				};
				console.log( blockType )

			});

		},

		delBlock: function () {
		
			$('.del-block').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);


				bootbox.dialog({
				  message: "Are you sure you wish to remove this block?",
				  title: "Remove Block Confirmation",
				  // size: "small",
				  buttons: {
				    cancel: {
				      label: "Cancel",
				      className: "btn-default btn-sm",
				      callback: function() {
				        
				      }
				    },
				    success: {
				      label: "Confirm",
				      className: "btn-primary btn-sm",
				      callback: function() {
				    	if ( $('.tiny-add-content').length !=2 ) {
							$this.parents('.content-block').next('.tiny-add-content').remove();	
						}else{
							$('.tiny-add-icon').removeClass('new-add')
						}
						
						$this.parents('.content-block').unbind().remove();

						var contentID = $this.parents('.content-block').data('content-id');
						if ( contentID ) {
							MSPB2.updateBlockStatus(contentID, 'deleted', function (data) {
								if ( data.is_valid ) {
									webArtJs.messenger({
										header: "",
										message: 'Content Block has been deleted!',
										closeAfter: 3000,
										position: "small top-left",
										closeBtn: true,
									});
								};
							})
						};

				      }
				    },
				  }
				});

			});

		},

		updateBlockStatus: function (id, status, callback) {
			$.ajax({
				url: base_url + 'cms/pages/set_status',
				type: 'POST',
				dataType: 'json',
				data: {
					id: id,
					status: status,
				},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(callback);
				
		},

		contentTools: function () {
	
			var options = {
			    valueNames: [ 'element-label' ]
			};

			var hackerList = new List('content-blocks-wrap', options);
			$('.content-element-wrapper body').isolatedScroll();


			var $pageBuilderTools = $('#page-builder-tools');

			$('.tiny-add-content').live('hover', function () {
				var $this = $(this);
				// if ( !$this.hasClass('active') && !$('#content-blocks-wrap').hasClass('go-animated') ) {
				// 	$pageBuilderTools.appendTo('#' + $this.parents('.tiny-add-content-wrap').attr('id'));
				// };
				
			});

			$('.tiny-add-content').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);
				

				// console.log("click")

				if ( !$this.hasClass('active') ) {
					MSPB2.closeContentElementWrap();
					MSPB2.openContentElementWrap($this);

					$('html,body').animate({
					   scrollTop: $this.offset().top - 100
					});

				}else{
					
					MSPB2.closeContentElementWrap();
				}

			});

			$(document).click(function(e){
			    if ($(e.target).closest(".add-content-wrap").length == 0 && $(e.target).closest(".tiny-add-content").length == 0) {
			        MSPB2.closeContentElementWrap();
			    }
			});

			if ( $('#first_para').length !=0 ) {
				MSPB2.contentTiny( '#first-para' );
			};

			$('.content-element-list > li > a').on('click', function (e) {
				e.preventDefault();
				var $this = $(this);
				
				$('.active-block').removeClass('active-block');

				// $('.btn-cancel-content').click();

				// $('.tiny-add-icon').addClass('new-add');

				setTimeout(function () {
					var CID  = $('.content-block').length + 1;
					CID  = CID + '-new';

					if ( $this.data('type') == "Grid" ) {
						var containerID = $('.container').length + 1;
						$('.tiny-add-content.active').parents('.tiny-add-content-wrap').replaceWith('<div data-type="Grid" class="content-block active-block grid-block"><div class="content-block-controls"><a class="btn btn-default del-block" href="#">Delete</a></div><div id="'+ containerID +'"><div class="row"></div></div></div>');

						MSPB2.gridDialog( containerID );
					};

					if ( $this.data('type') == "Text" ) {
						tinymceCount++;
						var paraID = 'paragraph-block-' + tinymceCount;
						$('.tiny-add-content.active').parents('.tiny-add-content-wrap').replaceWith('<div data-type="Paragraph" id="content-block-'+CID+'" class="content-block active-block paragraph-block"><div class="content-block-controls"><a class="btn btn-default del-block" href="#">Delete</a></div><div id="'+ paraID +'" class="content-block-editor"><p class="text-muted">Write here...</p></div></div>');

						MSPB2.contentTiny( '#' + paraID );
					};

					if ( $this.data('type') == "Markdown" ) {
						tinymceCount++;
						var paraID = 'paragraph-block-' + tinymceCount;
						$('.tiny-add-content.active').parents('.tiny-add-content-wrap').replaceWith('<div data-type="Paragraph" id="content-block-'+tinymceCount+'" class="content-block active-block paragraph-block"><div class="content-block-controls"><a class="btn btn-default del-block" href="#">Delete</a></div><div id="'+ paraID +'" class="content-block-editor"><p class="text-muted">Write here...</p></div></div>');

						MSPB2.contentTiny( '#' + paraID );
						MSPB2.markdownDialog( paraID );
					};					

					if ( $this.data('type') == "Quote" ) {
						$('.tiny-add-content.active').parents('.tiny-add-content-wrap').replaceWith('<div data-type="Quote" id="content-block-'+CID+'" class="content-block active-block quote-block animated fadeIn"><div class="content-block-controls"><a class="btn btn-default del-block" href="#">Delete</a></div><figure id="blockquote-figure-'+CID+'" class="blockquote-figure"><blockquote id="quote-block-'+CID+'" class="blockquote quote-block-wrapper">Quote...</blockquote><figcaption id="quote-block-caption-'+CID+'" class="source">Source here...</figcaption></figure></div>');
						MSPB2.quoteBlock(CID);
					};

					if ( $this.data('type') == "Image" ) {
						$('.tiny-add-content.active').parents('.tiny-add-content-wrap').replaceWith('<div data-type="Image" id="content-block-'+CID+'" class="content-block active-block image-block animated fadeIn"><div class="content-block-controls"><a data-id="'+CID+'" class="btn btn-default edit-block" href="#">Edit</a><a class="btn btn-default del-block" href="#">Delete</a></div><div class="image-block-wrapper"><div class="image-caption-wrap"></div></div></div>');
						MSPB2.imageDialog(CID);
					};

					if ( $this.data('type') == "Video" ) {
						$('.tiny-add-content.active').parents('.tiny-add-content-wrap').replaceWith('<div data-type="Video" id="content-block-'+CID+'" class="content-block active-block video-block animated fadeIn"><div class="content-block-controls"><a class="btn btn-default del-block" href="#">Delete</a></div><div class="video-block-wrapper embed-responsive embed-responsive-16by9"></div></div>');
						MSPB2.videoDialog(CID);
					};

					if ( $this.data('type') == "Embed" ) {
						$('.tiny-add-content.active').parents('.tiny-add-content-wrap').replaceWith('<div data-type="Embed" id="content-block-'+CID+'" class="content-block active-block embed-block animated fadeIn"><div class="content-block-controls"><a class="btn btn-default del-block" href="#">Delete</a></div><div class="embed-block-wrapper embed-responsive embed-responsive-16by9"></div></div>');
						MSPB2.embedDialog(CID);
					};

					if ( $this.data('type') == "Summary Wall" || $this.data('type') == "Summary Carousel" || $this.data('type') == "Summary List" || $this.data('type') == "Summary Grid" ) {
						$('.tiny-add-content.active').parents('.tiny-add-content-wrap').replaceWith('<div data-type="'+ $this.data('type') +'" id="content-block-'+CID+'" class="content-block active-block summary-block animated fadeIn"><div class="content-block-controls"><a class="btn btn-default del-block" href="#">Delete</a></div><div class="msis-summary-container"></div></div>');
						MSPB2.summaryDialog(CID, $this.data('type'));
					};


					if ( $this.data('type') == "Spacer" ) {
						$('.tiny-add-content.active').parents('.tiny-add-content-wrap').replaceWith('<div data-type="Spacer" id="content-block-'+CID+'" class="content-block active-block spacer-block active animated fadeIn"><div class="content-block-controls"><a class="btn btn-default del-block" href="#">Delete</a></div><div class="spacer-block-wrapper"><div class="spacer-block-content"></div></div></div>');
						MSPB2.resizeSpacer();
					};

					if ( $this.data('type') != "Spacer" ) {
						MSPB2.resizeBlock()
					};
					
					// console.log( $('.active-block').next().hasClass('tiny-add-content') )

					var $imageBlock = $('#content-block-'+CID);

					if ( !$imageBlock.next().hasClass('tiny-add-content') ) {
						var tinyWrapID = 'tiny-add-content-wrap' + $('.tiny-add-content-wrap').length + 1;
						$imageBlock.after('<div class="tiny-add-content-wrap" id="'+tinyWrapID+'"><a href="#" class="tiny-add-content animated"><span class="tiny-add-icon ion-plus-round"></span></a></div>');
					};

					// console.log( $('.active-block').next().html() )


					console.log($this.data('type'))

					MSPB2.closeContentElementWrap();

				}, 100);

				

			});

			$.each($('.content-block.paragraph-block'), function(index, val) {
				var $this = $(this);
				MSPB2.contentTiny( '#' + $this.find('.content-block-editor').attr('id') );
			});


		},

		gridDialog: function (containerID) {
		
			if ( getFormQuery == 1 ) return;
			getFormQuery = 1;

			$.ajax({
				url: base_url + 'cms/pages/get_form',
				type: 'POST',
				dataType: 'json',
				data: {
					form: 'grid',
					media_id: containerID,
				},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				getFormQuery = 0;
				if ( data.is_valid ) {
					// console.log(data.form)
					
					$('#msdialog').unbind();
					$('#msdialog').msdialog({
						title: "Grid",
						content: data.form,
						height: 223,
						width: 400,
						is_visible: null,
						position: {
							right: '20px',
							bottom: '60px',
						},
						footer: '<div class="form-inline">'+
									// '<div class="form-group right10">'+
									// 	'<a class="btn btn-sm btn-default cancel-block">Cancel</a>'+
									// '</div>'+	
									'<div class="form-group">'+
										'<a class="btn btn-sm btn-primary close-active-block-btn">Dismiss</a>'+
									'</div>'+
								'</div>',
						created: function () {
							setTimeout(function () {
								$('#msdialog').find('.main-dialog-wrapper').addClass('visible')
							}, 100)

							$(".main-dialog-wrapper").draggable({
							    handle: ".dialog-header",
							});	

							MSPB2.gridBlockSettings();
							
						}

					});



				};
				console.log("complete");
			});

		},

		gridBlockSettings: function () {
		
			$('#grid_num').live('change', function () {
				var $this = $(this);
				var value = $this.val();
				var media = 'xs';
				var $prevWrap = $('#grid-preview-wrap');
				var $prevWrapBlock = $('.active-block').find('.row');
				
				$prevWrap.html('')
				$prevWrapBlock.html('')

				if ( value == 1 ) {
					$prevWrap.append( MSPB2.getGrid(media, 12) );
					$prevWrapBlock.append( MSPB2.getGrid(media, 12) );
				};
				if ( value == 2 ) {
					$prevWrap.append( MSPB2.getGrid(media, 6) + MSPB2.getGrid(media, 6) );
					$prevWrapBlock.append( MSPB2.getGrid(media, 6) + MSPB2.getGrid(media, 6) );
				};
				if ( value == 3 ) {
					$prevWrap.append( MSPB2.getGrid(media, 4) + MSPB2.getGrid(media, 4) + MSPB2.getGrid(media, 4) );
					$prevWrapBlock.append( MSPB2.getGrid(media, 4) + MSPB2.getGrid(media, 4) + MSPB2.getGrid(media, 4) );
				};
				if ( value == "3 & 9" ) {
					$prevWrap.append( MSPB2.getGrid(media, 3) + MSPB2.getGrid(media, 9) );
					$prevWrapBlock.append( MSPB2.getGrid(media, 3) + MSPB2.getGrid(media, 9) );
				};
				if ( value == "9 & 3" ) {
					$prevWrap.append( MSPB2.getGrid(media, 9) + MSPB2.getGrid(media, 3));
					$prevWrapBlock.append( MSPB2.getGrid(media, 9) + MSPB2.getGrid(media, 3));
				};
				if ( value == 4 ) {
					$prevWrap.append( MSPB2.getGrid(media, 3) + MSPB2.getGrid(media, 3) + MSPB2.getGrid(media, 3) + MSPB2.getGrid(media, 3) );
					$prevWrapBlock.append( MSPB2.getGrid(media, 3) + MSPB2.getGrid(media, 3) + MSPB2.getGrid(media, 3) + MSPB2.getGrid(media, 3) );
				};
				if ( value == "4 & 8" ) {
					$prevWrap.append( MSPB2.getGrid(media, 4) + MSPB2.getGrid(media, 8));	
					$prevWrapBlock.append( MSPB2.getGrid(media, 4) + MSPB2.getGrid(media, 8));	
				};
				if ( value == "8 & 4" ) {
					$prevWrap.append( MSPB2.getGrid(media, 8) + MSPB2.getGrid(media, 4));	
					$prevWrapBlock.append( MSPB2.getGrid(media, 8) + MSPB2.getGrid(media, 4));	
				};
				if ( value == "5 & 7" ) {
					$prevWrap.append( MSPB2.getGrid(media, 5) + MSPB2.getGrid(media, 7));		
					$prevWrapBlock.append( MSPB2.getGrid(media, 5) + MSPB2.getGrid(media, 7));		
				};
				if ( value == "7 & 5" ) {
					$prevWrap.append( MSPB2.getGrid(media, 7) + MSPB2.getGrid(media, 5));		
					$prevWrapBlock.append( MSPB2.getGrid(media, 7) + MSPB2.getGrid(media, 5));		
				};

				$('.active-block').data('grid-num', $(this).val());

				$('.boot-grid').resizable({
			    	grid: 50,
			    	handles: "e, s",
			    	resize: function (event, ui) {
			    		var originalSize = ui.originalSize;
			    		var $bootGrid = ui.element; 
			    		var num_grid = $bootGrid.data('num-grid');
			    		var media = $bootGrid.data('media');

			    		console.log(ui)
			    		console.log( ui.size.width )
			    		console.log(media)
			    		console.log(num_grid)
			    		
			    		// ui.element.removeAttr('style')

			    		if ( ui.size.width > originalSize.width ) {
			    			var new_num_grid = num_grid + 1;
			    			$bootGrid.data('num-grid', new_num_grid).removeClass('col-'+media+'-'+ num_grid).addClass('col-'+media+'-'+ new_num_grid);
			    		}else{
			    			var new_num_grid = num_grid - 1;
			    			$bootGrid.data('num-grid', new_num_grid).removeClass('col-'+media+'-'+ num_grid).addClass('col-'+media+'-'+ new_num_grid);
			    		}
			    	},
			    	stop: function (event, ui) {
			    		ui.element.removeAttr('style')
			    	}
			    });

				// $( ".content-block" ).draggable({ handle: "p" });

				$(".content-block").draggable({
					opacity: 0.7,
			        revert:  function(dropped) {
			             var $draggable = $(this),
			                 hasBeenDroppedBefore = $draggable.data('hasBeenDropped'),
			                 wasJustDropped = dropped && dropped[0].id == "droppable";
			             if(wasJustDropped) {
			                 // don't revert, it's in the droppable
			                 return false;
			             } else {
			                 if (hasBeenDroppedBefore) {
			                     // don't rely on the built in revert, do it yourself
			                     $draggable.animate({ top: 0, left: 0 }, 'slow');
			                     return false;
			                 } else {
			                     // just let the built in revert work, although really, you could animate to 0,0 here as well
			                     return true;
			                 }
			             }
			        }
			    });

			    $(".content-block").draggable({
			        revert : function(event, ui) {
			            $(this).data("uiDraggable").originalPosition = {
			                top : 0,
			                left : 0
			            };
			           
			            return !event;
			        }
			    });

			    $(".grid-prev-box").droppable({
			        activeClass: 'ui-state-hover',
			        hoverClass: 'ui-state-active',
			        drop: function(event, ui) {
			        	console.log( $(this) )
			        	$(this).html( $(ui.draggable).html() );


			            $(this).addClass('ui-state-highlight');
			            $(ui.draggable).data('hasBeenDropped', true);
			            $(ui.draggable).remove()
			        	return true;
			        }
			    });

			});
	

			$('.close-active-block-btn').live('click', function (e) {
				e.preventDefault();
				$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');
				$('.active-block').removeClass('active-block');
			});
		},

		getGrid: function (media, num_grid) {
			return '<div data-media="'+media+'" data-num-grid="'+num_grid+'" class="col-'+media+'-'+num_grid+' col-small-gutter boot-grid"><div class="grid-prev-box"></div></div>';
		},

		markdownDialog: function (paraID) {
			
			var html = '<div class="pad10">'+
							'<div class="container-fluid">'+
								'<div class="row">'+
									'<div class="col-xs-12">'+
										'<div class="mardown-wrapper">' +
											'<div class="btn-toolbar markdown-toolbar hide">' +
												'<div class="btn-group btn-group-xs">'+
													'<button data-action="bold" type="button" class="btn btn-default markdown-control-btn"><span class="glyphicon glyphicon-bold"></span></button>' +
													'<button data-action="italic" type="button" class="btn btn-default markdown-control-btn"><span class="glyphicon glyphicon-italic"></span></button>' +
													'<button data-action="link" type="button" class="btn btn-default markdown-control-btn"><span class="glyphicon glyphicon-link"></span></button>' +
												'</div>' +
												'<div class="btn-group btn-group-xs">'+
													'<button data-action="h1" type="button" class="btn btn-default markdown-control-btn">h1</button>' +
													'<button data-action="h2" type="button" class="btn btn-default markdown-control-btn">h2</button>' +
												'</div>' +
												'<div class="btn-group btn-group-xs">'+
													'<button data-action="quote" type="button" class="btn btn-default markdown-control-btn">Quote</button>' +
													'<button data-action="list" type="button" class="btn btn-default markdown-control-btn">List</button>' +
													'<button data-action="numlist" type="button" class="btn btn-default markdown-control-btn">Num List</button>' +
												'</div>' +
											'</div>' +
											'<div class="clearfix top10"></div>' +
											'<textarea id="markdown-'+paraID+'" name="content" class="form-control markdown-textarea" style="min-height: 300px;"></textarea>'+
										'</div>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>';

								
			$('#msdialog').unbind();
			$('#msdialog').msdialog({
				title: "Markdown",
				content: html,
				height: $(window).height() - 200,
				width: 500,
				is_visible: null,
				position: {
					right: '20px',
					bottom: '60px',
				},
				footer: '<div class="form-inline">'+
							// '<div class="form-group right10">'+
							// 	'<a id="cancel-markdown-'+paraID+'" class="btn btn-sm btn-default cancel-block">Cancel</a>'+
							// '</div>'+	
							'<div class="form-group">'+
								'<a id="save-markdown-'+paraID+'" class="btn btn-sm btn-primary">Save</a>'+
							'</div>'+
						'</div>',
				created: function () {
					setTimeout(function () {
						$('#msdialog').find('.main-dialog-wrapper').addClass('visible')
					}, 100)

					$(".main-dialog-wrapper").draggable({
					    handle: ".dialog-header",
					});	

					MSPB2.markdownBlock(paraID);
					
				}

			});

		},

		markdownBlock: function (paraID) {
			
			// var markdownVal = $("#markdown-"+ paraID).val();

			MSPB2.converter = new showdown.Converter(),
		    
		    $("#markdown-"+ paraID).focus().on('keyup', function (e) {
		    	var markdownVal = $(this).val(),
		    	html      = MSPB2.converter.makeHtml(markdownVal);
			    $('#'+paraID).html(html);

		    });

		    autosize($('#markdown-'+paraID));

		    $('#save-markdown-'+ paraID).on('click', function (e) {
				e.preventDefault();
				$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');
				$('.active-block').removeClass('active-block');
			});

			$('#cancel-markdown-'+ paraID).on('click', function (e) {
				e.preventDefault();

				bootbox.dialog({
				  message: "Are you sure you wish to remove this block?",
				  title: "Remove Block Confirmation",
				  // size: "small",
				  buttons: {
				    cancel: {
				      label: "Cancel",
				      className: "btn-default btn-sm",
				      callback: function() {
				        
				      }
				    },
				    success: {
				      label: "Confirm",
				      className: "btn-primary btn-sm",
				      callback: function() {

				    	$('#'+ paraID).parents('.content-block').remove();
				    	$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');
						$('.active-block').removeClass('active-block');

				      }
				    },
				  }
				});
				
			});


			// $('.markdown-control-btn').on('click', function (e) {
			// 	e.preventDefault();
			// 	var $this = $(this);
			// 	var action = $this.data('action');
			// });

		},

		quoteBlock: function (quoteID) {
			
			var text = $('#blockquote-figure-'+ quoteID).find('.quote-block-wrapper').text();
			var caption = $('#blockquote-figure-'+ quoteID).find('.source').text();
			
			var html = '<div class="pad20">'+
							'<div class="container-fluid">'+
								'<div class="row">'+
									'<div class="col-xs-12">'+
										'<div class="blockquote-wrapper">' +
											'<div class="form-group"><textarea id="quote-textarea-'+quoteID+'" name="content" class="form-control blockquote-textarea" style="min-height: 145px;">'+ text +'</textarea></div>'+
											'<div class="form-group bottom0"><input id="quote-source-'+quoteID+'" type="text" class="form-control blockquote-source" placeholder="Enter source" name="source" value="'+ caption +'" /></div>'+
										'</div>'+
									'</div>'+
								'</div>'+
							'</div>'+
						'</div>';
								
			$('#msdialog').unbind();
			$('#msdialog').msdialog({
				title: "Quote",
				content: html,
				height: 234,
				width: 400,
				is_visible: null,
				position: {
					right: '20px',
					bottom: '60px',
				},
				footer: '<div class="form-inline">'+
							// '<div class="form-group right10">'+
							// 	'<a id="cancel-quote-'+quoteID+'" class="btn btn-sm btn-default">Cancel</a>'+
							// '</div>'+	
							'<div class="form-group">'+
								'<a id="save-quote-'+quoteID+'" class="btn btn-sm btn-primary">Dismiss</a>'+
							'</div>'+
						'</div>',
				created: function () {
					setTimeout(function () {
						$('#msdialog').find('.main-dialog-wrapper').addClass('visible')
					}, 100)

					$(".main-dialog-wrapper").draggable({
					    handle: ".dialog-header",
					});					

					MSPB2.quoteBlockSettings(quoteID)
				}

			});


		},

		quoteBlockSettings: function (quoteID) {
		
			$('.blockquote-textarea').focus().on('keyup', function () {
				var $this = $(this);
				$('.active-block').find('.blockquote').text( $this.val() );
			});

			$('.blockquote-source').on('keyup', function () {
				var $this = $(this);
				$('.active-block').find('.source').text( $this.val() );
			});


			$('#save-quote-'+quoteID).on('click', function (e) {
				e.preventDefault();
				$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');

				if ( $('.active-block').find('.content-block-controls').find('.edit-block').length == 0 ) {
					$('.active-block').find('.content-block-controls').prepend('<a data-id="'+quoteID+'" class="btn btn-default edit-block" href="#">Edit</a>');
				};
				$('.active-block').removeClass('active-block');
			});

			$('#cancel-quote-'+quoteID).on('click', function (e) {
				e.preventDefault();

				bootbox.dialog({
				  message: "Are you sure you wish to remove this block?",
				  title: "Remove Block Confirmation",
				  // size: "small",
				  buttons: {
				    cancel: {
				      label: "Cancel",
				      className: "btn-default btn-sm",
				      callback: function() {
				        
				      }
				    },
				    success: {
				      label: "Confirm",
				      className: "btn-primary btn-sm",
				      callback: function() {

				    	// $('.content-block.active-block').remove();
				    	$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');
						$('.active-block').removeClass('active-block');

				      }
				    },
				  }
				});
				
			});
		},

		imageDialog: function (block_id, media_id) {

			if ( getFormQuery == 1 ) return;
			getFormQuery = 1;

			$.ajax({
				url: base_url + 'cms/pages/get_form',
				type: 'POST',
				dataType: 'json',
				data: {
					form: 'image_block',
					media_id: media_id,
				},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				getFormQuery = 0;
				if ( data.is_valid ) {
					// console.log(data.form)
					
					$('#msdialog').unbind();
					$('#msdialog').msdialog({
						title: "Image",
						content: data.form,
						height: $(window).height() - 200,
						width: 400,
						is_visible: null,
						position: {
							right: '20px',
							bottom: '60px',
						},
						footer: '<div class="form-inline">'+
									'<div class="form-group right10">'+
										'<a class="btn btn-sm btn-default cancel-block">Cancel</a>'+
									'</div>'+	
									'<div class="form-group">'+
										'<a class="btn btn-sm btn-primary close-active-block-btn">Save</a>'+
									'</div>'+
								'</div>',
						created: function () {
							setTimeout(function () {
								$('#msdialog').find('.main-dialog-wrapper').addClass('visible')
							}, 100)

							// console.log("created")

							MSPB2.newImageUploader(block_id);

							$(".main-dialog-wrapper").draggable({
							    handle: ".dialog-header",
							});	

							MSPB2.imageBlockSettings(block_id, media_id);
							
							
						},

					});



				};
				console.log("complete");
			});

		},

		imageBlockSettings: function (block_id, media_id) {

			var $imageBlock = $('#content-block-'+block_id);
			var $captionBlock = $('#image-block-caption-'+block_id);

			console.log($imageBlock.data())
			$('#image_caption_placement').live('change', function () {
				var $this = $(this);
				$imageBlock.data('caption-placement', $this.val());
				
				if ( $this.val() == "Do not display caption" ) {
					$('#image-caption-form-group').addClass('hide');
				}else{
					$('#image-caption-form-group').removeClass('hide');
				}

			});

			
			$('#stretch_attachment').live('change', function (e) {
				var $this = $(this);
				if ( $this.is(':checked') ) {
					// console.log("Checked")
					$imageBlock.data('stretch', 1);
				}else{
					$imageBlock.data('stretch', 0);
					// console.log("Unchecked")							
				}
			});

			$('#image_lightbox').live('change', function (e) {
				var $this = $(this);
				var $imageUrl = $('#image-url-link');

				if ( $this.is(':checked') ) {
					$imageBlock.data('lightbox', 1);
					$imageBlock.data('url', '');
					$imageUrl.val('')
					$('#image-url-wrap').addClass('hide');
					// console.log("Checked")
				}else{
					$imageBlock.data('lightbox', 0);
					$imageBlock.data('url', $imageUrl.val());
					$('#image-url-wrap').removeClass('hide');
					// console.log("Unchecked")							
				}
			});

			$('#open_in_new_window').live('change', function (e) {
				var $this = $(this);

				if ( $this.is(':checked') ) {
					$imageBlock.data('new-window', 1);
				}else{
					$imageBlock.data('new-window', 0);
				}
			});

			if ( $imageBlock.data('caption-placement') ) {

				if ( $imageBlock.data('caption-placement') == "Do not display caption" ) {
					$('#image-caption-form-group').addClass('hide');
				}else{
					$('#image-caption-form-group').removeClass('hide');
				}


				$('#image_caption_placement').val( $imageBlock.data('caption-placement') );
			};

			if ( $imageBlock.data('image-caption') ) {
				$('#image_caption').val( $imageBlock.data('image-caption') );
			};

			if ( $imageBlock.data('stretch') ) {
				$('#stretch_attachment').prop('checked', 'checked');
			};

			if ( $imageBlock.data('new-window') ) {
				$('#open_in_new_window').prop('checked', 'checked');
			};

			if ( $imageBlock.data('lightbox') ) {
				$('#image_lightbox').prop('checked', 'checked');
				$('#image-url-link').val( '' );
				$imageBlock.data('url', '');
				$('#image-url-wrap').addClass('hide');
			};
			if ( $imageBlock.data('url') ) {
				$('#image-url-link').val( $('.active-block').data('url') );
			};

			if ( tinymce.get('image_caption') ) {
				tinymce.get('image_caption').remove();	
				
			}
			


			MSAdmin.tinyMCE();

			setTimeout(function() {
				tinymce.activeEditor.on('change', function(e) {
					$imageBlock.data('image-caption', $('#image_caption').val())

					$captionBlock.html( $('#image_caption').val() );	
					// console.log( $('#image_caption').val() )
				});


			}, 300);


			$('.close-active-block-btn').live('click', function (e) {
				e.preventDefault();
				if ( $.trim($('#image-url-link').val()).length !=0 ) {
					$imageBlock.data('url', $('#image-url-link').val());
				};
				$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');
				$imageBlock.removeClass('active-block');

			});

			$('.cancel-block').live('click', function (e) {
				e.preventDefault();
				$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');
				$imageBlock.removeClass('active-block');
				// $imageBlock.remove();

			});

		},

		newImageUploader: function (block_id) {
			
			var $imageBlock = $('#content-block-'+block_id);

			var	$dropElement = $('#new-image-wrapper')
			,	max_file_count = 1;

			var $currentImageWrap = $('#current-image-wrap');
			var $preloader = $('#new-image-preloader');
			var $newimagepickfile = $('#newimagepickfile');

			console.log( $newimagepickfile.length )

			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'newimagepickfile', // you can pass an id...
				drop_element: 'new-image-wrapper',
				dragdrop: true,
				container: document.getElementById('new-image-pickfiles-container'), // ... or DOM Element itself
				url : base_url + 'cms/media/do_upload',
				flash_swf_url : base_url + 'js/vendor/Moxie.swf',
				silverlight_xap_url : base_url + 'js/vendor/Moxie.xap',
				filters : {
					max_file_size: '100mb',
					mime_types: [
						{title : "Image files", extensions : "jpg,jpeg,gif,png"},
					]
				},
				multi_selection: false,
				multipart_params : {
			        "attach_id" : currentURL.query.id,
			        "resize" : 1,
			        "attachment_for" : "image_block",
			    }
			});
			// console.log(uploader);

			uploader.init();



			// UploadProgress
			uploader.bind('UploadProgress', function (up, file) {
				$('#'+file.id).find('.progress-bar').css('width', file.percent + '%').find('.sr-only').text(file.percent + '%');
				
				console.log(file.percent)

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
					$preloader.show().html('<div id="'+ file.id +'"><div class="media-preview file-detail files-added">'+ '<div class="progress"><div class="progress-bar progress-bar-striped active" style="width: 20%" role="progressbar"><span class="sr-only"></span></div></div></div></div>');
				};
				uploader.start();
				uploader.refresh();
			});

			// Error
			uploader.bind('Error', function (up, err) {
				console.log(up)
				console.log(err.response)
				if ( $('#'+ err.file.id).length !=0 ) {
					$preloader.addClass('error-file').html('<div class="media-preview file-detail">' + '<div class="text-danger">'+ err.message +'</div>' +'</div>');
				}else{
					$preloader.html('<div id="'+ err.file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ err.message +'</div>' +'</div></div>');
				}
				uploader.refresh();
			});



			uploader.bind('FileUploaded', function (up, file, response) {
				console.log(response)
				
				var jsonResponse = $.parseJSON(response.response);

				console.log(jsonResponse)

				if ( jsonResponse.is_valid ) {
					$('.msis-error-message-wrapper').hide();
					$dropElement.addClass('has-image');
					$newimagepickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Change Image');

					// $('#logo_image').val( jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name );
					var imgString = '<img data-media-id="'+ jsonResponse.upload_data.media_id +'" class="img-responsive margin-auto animated fadeIn" src="'+ base_url + jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name  +'">';


					$currentImageWrap.html( imgString );
					if ( $imageBlock.find('.edit-block').length == 0 ) {
						console.log($imageBlock.data('media-id'))
						$imageBlock.find('.content-block-controls').prepend('<a data-id="'+ jsonResponse.upload_data.media_id +'" data-media-id="'+jsonResponse.upload_data.media_id+'" class="btn btn-default edit-block" href="#">Edit</a>');
					}else{
						// set edit block id
						$imageBlock.find('.edit-block').data('media-id', jsonResponse.upload_data.media_id);
					}

					$imageBlock.find('.image-block-wrapper').replaceWith( '<div class="content-block-image">'+ imgString +'</div>' );
					if ( $imageBlock.find('.content-block-image').length !=0 ) {
						$imageBlock.find('.content-block-image').html(imgString)
					};

					$imageBlock.data('media-id', jsonResponse.upload_data.media_id);
					$imageBlock.data('path', jsonResponse.upload_data.upload_full_path);
					$imageBlock.data('file-name', jsonResponse.upload_data.file_name);
					
					uploader.removeFile(file.id);

					// submit the logo-title-form
					// $('#logo-title-form').submit();
					MSPB2.resizeBlock()

				};

				if ( !jsonResponse.is_valid ) {
					$newimagepickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Add an Image');
					$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
					uploader.refresh();
				};

			});

		},

		embedDialog: function (embedID) {
			
			if ( getFormQuery == 1 ) return;
			getFormQuery = 1;

			$.ajax({
				url: base_url + 'cms/pages/get_form',
				type: 'POST',
				dataType: 'json',
				data: {
					form: 'embed_block',
					block_id: embedID,
				},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				getFormQuery = 0;
				if ( data.is_valid ) {
					// console.log(data.form)
					
					$('#msdialog').unbind();
					$('#msdialog').msdialog({
						title: "Embed",
						content: data.form,
						height: $(window).height() - 200,
						width: 400,
						is_visible: null,
						position: {
							right: '20px',
							bottom: '60px',
						},
						footer: '<div class="form-inline">'+
									// '<div class="form-group right10">'+
									// 	'<a id="cancel-video-'+embedID+'" class="btn btn-sm btn-default cancel-block">Cancel</a>'+
									// '</div>'+	
									'<div class="form-group">'+
										'<a id="save-embed-'+embedID+'" class="btn btn-sm btn-primary close-active-block-btn">Dismiss</a>'+
									'</div>'+
								'</div>',
						created: function () {
							setTimeout(function () {
								$('#msdialog').find('.main-dialog-wrapper').addClass('visible')
							}, 100)

							$('#video_url').focus().val($('.active-block').data('video-url'));
							
							// console.log("created")
							// MSPB2.newImageUploader();

							$(".main-dialog-wrapper").draggable({
							    handle: ".dialog-header",
							});	

							MSPB2.embedBlockSettings(embedID);
							
						}

					});



				};
				console.log("complete");
			});

		},

		embedBlockSettings: function (embedID) {
			var currentVideoURL;

			// $('#video_url').on('change', function (e) {
			// 	var $this = $(this);

			// 	if ( currentVideoURL != $this.val() ) {
			// 		currentVideoURL = $this.val();
			// 		MSPB2.getVideoID(embedID, $this.val());	
			// 	};
				
			// });
			var $wrapper = $('#content-block-'+embedID).find('.embed-block-wrapper');
			var embedVal = $wrapper.data('html');
			var alignment = $wrapper.data('alignment');
			
			console.log("embed-input")

			console.log(alignment);
			console.log(embedVal);
			if ( embedVal !=null ) {
				$('#embed-input').val( embedVal );
			};

			if ( alignment !=null ) {
				if ( alignment == "Left" ) {
					$('#align_left').prop('checked', true)
				};
				if ( alignment == "Center" ) {
					$('#align_center').prop('checked', true)
				};
				if ( alignment == "Right" ) {
					$('#align_right').prop('checked', true)
				};
			};

			$('#embed-input').focus().on('change', function () {
				var $this = $(this);
				// embedVal = $this.val();
				$('.active-block').find('.embed-block-wrapper').html( $this.val() ).data('html', $this.val());
			});

			$('#embed-alignment input').on('change', function () {
				var $this = $(this);

				$wrapper.removeClass('tal').removeClass('margin-auto').removeClass('tac').removeClass('tar');

				if ( $this.val() == "Left" ) {
					$wrapper.addClass('tal');
				};
				if ( $this.val() == "Center" ) {
					$wrapper.addClass('margin-auto').addClass('tac');
				};
				if ( $this.val() == "Right" ) {
					$wrapper.addClass('tar');
				};

				$wrapper.data('alignment', $this.val());
			});


			$('#save-embed-'+embedID).on('click', function (e) {
				e.preventDefault();
				$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');

				if ( $('.active-block').find('.content-block-controls').find('.edit-block').length == 0 ) {
					$('.active-block').find('.content-block-controls').prepend('<a data-id="'+embedID+'" class="btn btn-default edit-block" href="#">Edit</a>');
				};
				$('.active-block').removeClass('active-block');
			});

			$('#cancel-video-'+embedID).on('click', function (e) {
				e.preventDefault();

				bootbox.dialog({
				  message: "Are you sure you wish to remove this block?",
				  title: "Remove Block Confirmation",
				  // size: "small",
				  buttons: {
				    cancel: {
				      label: "Cancel",
				      className: "btn-default btn-sm",
				      callback: function() {
				        
				      }
				    },
				    success: {
				      label: "Confirm",
				      className: "btn-primary btn-sm",
				      callback: function() {

				    	// $('.content-block.active-block').remove();
				    	$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');
						$('.active-block').removeClass('active-block');

				      }
				    },
				  }
				});
				
			});



		
		},

		summaryDialog: function (block_id, type) {
			
			if ( getFormQuery == 1 ) return;
			getFormQuery = 1;

			$.ajax({
				url: base_url + 'cms/pages/get_form',
				type: 'POST',
				dataType: 'json',
				data: {
					form: 'summary_block',
					block_id: block_id,
					type: type,
				},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				getFormQuery = 0;
				if ( data.is_valid ) {
					// console.log(data.form)
					
					$('#msdialog').unbind();
					$('#msdialog').msdialog({
						title: "Summary",
						content: data.form,
						height: $(window).height() - 200,
						width: 400,
						is_visible: null,
						position: {
							right: '20px',
							bottom: '60px',
						},
						footer: '<div class="form-inline">'+
									'<div class="form-group right10">'+
										'<a id="dismiss-'+block_id+'" class="btn btn-sm btn-default cancel-block">Dismiss</a>'+
									'</div>'+	
									'<div class="form-group">'+
										'<a id="preview-summary-'+block_id+'" class="btn btn-sm btn-primary close-active-block-btn">Preview</a>'+
									'</div>'+
								'</div>',
						created: function () {
							setTimeout(function () {
								$('#msdialog').find('.main-dialog-wrapper').addClass('visible')
							}, 100)

							$(".main-dialog-wrapper").draggable({
							    handle: ".dialog-header",
							});	

							MSPB2.summaryBlockSettings(block_id);
							
						}

					});



				};
				console.log("complete");
			});

		},

		summaryBlockSettings: function (block_id) {
			console.log(block_id)
			var $wrapper = $('#content-block-'+block_id).find('.msis-summary-container');
			var type = $wrapper.data('type');
			var blog_id = $wrapper.data('blog-id');

			// DISPLAY
			var no_of_items = $wrapper.data('no-of-items');

			var showTitle = $wrapper.data('show-title');
			var showThumbnail = $wrapper.data('show-thumbnail');
			var showExcerpt = $wrapper.data('show-excerpt');
			var showReadMoreLink = $wrapper.data('show-read-more-link');


			var summaryFullWidth = $wrapper.data('summary-full-width');

			// WALL TYPE
			var wall_itemsPerRow = $wrapper.data('wall-items-per-row');
			var wallGutterWidth = $wrapper.data('wall-gutter-width');

			// CAROUSEL TYPE
			var carHeaderText = $wrapper.data('header-text');
			var carAspectRatio = $wrapper.data('car-aspect-ratio');
			var carItemsPerRow = $wrapper.data('car-items-per-row');
			
			// LIST TYPE
			var listAspectRatio = $wrapper.data('list-aspect-ratio');
			var listImageSize = $wrapper.data('list-image-size');
			var listImageAlignment = $wrapper.data('list-image-alignment');

			// GRID TYPE
			var gridAspectRatio = $wrapper.data('grid-aspect-ratio');
			var gridColumnWidth = $wrapper.data('grid-items-per-row');
			var gridGutterWidth = $wrapper.data('grid-gutter-width');


			var textSize = $wrapper.data('text-size');
			var textAlignment = $wrapper.data('text-alignment');
			var metaDataPosition = $wrapper.data('metadata-position');

			
			// console.log(alignment);
			// console.log(embedVal);



			$('.blog-pages-select input').on('change', function (e) {
				e.preventDefault();
				var $this = $(this);
				
				if ( $this.is(':checked') ) {
					console.log( $this.val() );
					$wrapper.data('blog-id', $this.val());
					console.log($wrapper.data())
				};
			});

			$('.summary-type').on('click', function (e) {
				var $this = $(this);
				$wrapper.data('type', $this.data('type'));
			});

			$('#summary_full_width').on('change', function (e) {
				var $this = $(this);
				if ( $this.is(':checked') ) {
					$wrapper.data('summary-full-width', 1);
				}else{
					$wrapper.data('summary-full-width', 0);
				}
				
			});


			if ( $('#show_title:checked').val() ) {
				$wrapper.data('show-title', $('#show_title:checked').val())	
			}
			
			$('#show_title').on('change', function (e) {
				var $this = $(this);
				if ( $this.is(':checked') ) {
					$wrapper.data('show-title', $this.val());
				}else{
					$wrapper.data('show-title', 0);
				}
				console.log( $this.val() );
			});


			if ( $('#show_thumbnail:checked').val() ) {
				$wrapper.data('show-thumbnail', $('#show_thumbnail:checked').val())	
			}

			$('#show_thumbnail').on('change', function (e) {
				var $this = $(this);
				if ( $this.is(':checked') ) {
					$wrapper.data('show-thumbnail', $this.val());
				}else{
					$wrapper.data('show-thumbnail', 0);
				}
				console.log( $this.val() );
			});


			if ( $('#show_excerpt:checked').val() ) {
				$wrapper.data('show-excerpt', $('#show_excerpt:checked').val())	
			}

			$('#show_excerpt').on('change', function (e) {
				var $this = $(this);
				if ( $this.is(':checked') ) {
					$wrapper.data('show-excerpt', $this.val());
				}else{
					$wrapper.data('show-excerpt', 0);
				}
				console.log( $this.val() );
			});


			if ( $('#show_read_more_link:checked').val() ) {
				$wrapper.data('show-read-more-link', $('#show_read_more_link:checked').val())	
			}
			$('#show_read_more_link').on('change', function (e) {
				var $this = $(this);
				if ( $this.is(':checked') ) {
					$wrapper.data('show-read-more-link', $this.val());
				}else{
					$wrapper.data('show-read-more-link', 0);
				}
				console.log( $this.val() );
			});


			if ( $('#featured_filter:checked').val() ) {
				$wrapper.data('featured-filter', $('#featured_filter:checked').val())	
			}
			
			$('#featured_filter').on('change', function (e) {
				var $this = $(this);
				if ( $this.is(':checked') ) {
					$wrapper.data('featured-filter', $this.val());
				}else{
					$wrapper.data('featured-filter', 0);
				}
				console.log( $this.val() );
			});


			$('#text_size').live('change', function () {
				$wrapper.data('text-size', $(this).val())
			});

			$('#text_alignment').live('change', function () {
				$wrapper.data('text-alignment', $(this).val())
			});

			$('#metadata_position').live('change', function () {
				$wrapper.data('metadata-position', $(this).val())
			});


			// WALL 
			// var columnWidthSlider = document.getElementById('column-width-slider');
			// noUiSlider.create(columnWidthSlider, {
			// 	start: $('#column-width-slider').data('start'),
			// 	step: 1,
			// 	range: {
			// 		'min': 50,
			// 		'max': 600
			// 	}
			// });

			// columnWidthSlider.noUiSlider.on('update', function( values, handle ){
			// 	var curVal = parseInt(values[handle]);
			// 	$('#column_width').val(curVal);
			// 	$('#column-width-val').text(curVal);
			// 	$wrapper.data('wall-items-per-row', curVal);
			// });

			var wallitemsPerRow = document.getElementById('wall-items-per-row-slider');
			noUiSlider.create(wallitemsPerRow, {
				start: $('#wall-items-per-row-slider').data('start'),
				step: 1,
				range: {
					'min': 2,
					'max': 6
				}
			});

			wallitemsPerRow.noUiSlider.on('update', function( values, handle ){
				var curVal = parseInt(values[handle]);
				if ( curVal == 5 ) {
					wallitemsPerRow.noUiSlider.set(6);
					curVal = 6;
				};
				$('#wall_items_per_row').val(curVal);
				$('#wall-items-per-row-val').text(curVal);

				$wrapper.data('wall-items-per-row', curVal);
			});

			var gutterWidthSlider = document.getElementById('gutter-width-slider');
			noUiSlider.create(gutterWidthSlider, {
				start: $('#gutter-width-slider').data('start'),
				step: 1,
				range: {
					'min': 0,
					'max': 100
				}
			});

			gutterWidthSlider.noUiSlider.on('update', function( values, handle ){
				var curVal = parseInt(values[handle]);
				$('#gutter_width').val(curVal);
				$('#gutter-width-val').text(curVal);
				$wrapper.data('wall-gutter-width', curVal);
			});



			// CAROUSEL
			
			$('#header_text').live('keyup', function () {
				$wrapper.data('header-text', $('#header_text').val() );
			});



			$('#carousel_aspect_ratio').live('change', function () {
				$wrapper.data('car-aspect-ratio', $(this).val() );
				
				MSPB2.clearImageAspectRatioClass( $wrapper );
				var aspectRatioClass = MSPB2.getImageAspectRatio( $(this).val() );
				$wrapper.addClass(aspectRatioClass);

			});

			var itemsPerRow = document.getElementById('carousel-items-per-row-slider');
			noUiSlider.create(itemsPerRow, {
				start: $('#carousel-items-per-row-slider').data('start'),
				step: 1,
				range: {
					'min': 1,
					'max': 5
				}
			});

			itemsPerRow.noUiSlider.on('update', function( values, handle ){
				var curVal = parseInt(values[handle]);
				$('#carousel_items_per_row').val(curVal);
				$('#carousel-items-per-row-val').text(curVal);

				$wrapper.data('car-items-per-row', curVal);
			});




			// LIST
			$('#list_aspect_ratio').live('change', function () {
				$wrapper.data('list-aspect-ratio', $(this).val() );
				MSPB2.clearImageAspectRatioClass( $wrapper );
				var aspectRatioClass = MSPB2.getImageAspectRatio( $(this).val() );
				$wrapper.addClass(aspectRatioClass);
			});				

			var imageSize = document.getElementById('image-size-slider');
			noUiSlider.create(imageSize, {
				start: $('#image-size-slider').data('start'),
				step: 1,
				range: {
					'min': 10,
					'max': 80
				}
			});

			imageSize.noUiSlider.on('update', function( values, handle ){
				var curVal = parseInt(values[handle]);
				$('#image_size').val(curVal);
				$('#image-size-val').text(curVal + '%');
				$wrapper.data('list-image-size', curVal);
			});


			$('#image_alignment').live('change', function () {
				$wrapper.data('list-image-alignment', $(this).val() );

				$wrapper.find('.post-image-wrap').removeClass('list-image-right').removeClass('list-image-left');
				$wrapper.find('.post-image-wrap').addClass( $(this).val() );
			});



			// GRID

			$('#grid_aspect_ratio').live('change', function () {
				$wrapper.data('grid-aspect-ratio', $(this).val())
				MSPB2.clearImageAspectRatioClass( $wrapper );
				var aspectRatioClass = MSPB2.getImageAspectRatio( $(this).val() );
				$wrapper.addClass(aspectRatioClass);
			});


			// var gridColumnWidthSlider = document.getElementById('grid-items-per-row-slider');
			// noUiSlider.create(gridColumnWidthSlider, {
			// 	start: $('#grid-items-per-row-slider').data('start'),
			// 	step: 1,
			// 	range: {
			// 		'min': 1,
			// 		'max': 5
			// 	}
			// });


			// gridColumnWidthSlider.noUiSlider.on('update', function( values, handle ){
			// 	var curVal = parseInt(values[handle]);
			// 	$('#grid_items_per_row').val(curVal);
			// 	$('#grid-items-per-row-val').text(curVal);
			// 	$wrapper.data('grid-items-per-row', curVal);
			// });


			var griditemsPerRow = document.getElementById('grid-items-per-row-slider');
			noUiSlider.create(griditemsPerRow, {
				start: $('#grid-items-per-row-slider').data('start'),
				step: 1,
				range: {
					'min': 2,
					'max': 6
				}
			});

			griditemsPerRow.noUiSlider.on('update', function( values, handle ){
				var curVal = parseInt(values[handle]);
				if ( curVal == 5 ) {
					griditemsPerRow.noUiSlider.set(6);
					curVal = 6;
				};
				$('#grid_items_per_row').val(curVal);
				$('#grid-items-per-row-val').text(curVal);

				$wrapper.data('grid-items-per-row', curVal);
			});


			var gridGutterWidthSlider = document.getElementById('grid-gutter-width-slider');
			noUiSlider.create(gridGutterWidthSlider, {
				start: $('#grid-gutter-width-slider').data('start'),
				step: 1,
				range: {
					'min': 0,
					'max': 100
				}
			});

			gridGutterWidthSlider.noUiSlider.on('update', function( values, handle ){
				var curVal = parseInt(values[handle]);
				$('#grid_gutter_width').val(curVal);
				$('#grid-gutter-width-val').text(curVal);
				$wrapper.data('grid-gutter-width', curVal);
			});




			// DISPLAY

			var noOfItems = document.getElementById('no-of-items-slider');
			noUiSlider.create(noOfItems, {
				start: $('#no-of-items-slider').data('start'),
				step: 1,
				range: {
					'min': 1,
					'max': 30
				}
			});

			noOfItems.noUiSlider.on('update', function( values, handle ){
				var curVal = parseInt(values[handle]);
				
				$('#no_of_items').val(curVal);
				$('#no-of-items-val').text(curVal);

				$wrapper.data('no-of-items', curVal);
			});



			$('#dismiss-'+block_id).on('click', function (e) {
				e.preventDefault();
				$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');

				if ( $('.active-block').find('.content-block-controls').find('.edit-block').length == 0 ) {
					$('.active-block').find('.content-block-controls').prepend('<a data-id="'+block_id+'" class="btn btn-default edit-block" href="#">Edit</a>');
				};
				$('.active-block').removeClass('active-block');
			});

			$('#cancel-video-'+block_id).on('click', function (e) {
				e.preventDefault();

				bootbox.dialog({
				  message: "Are you sure you wish to remove this block?",
				  title: "Remove Block Confirmation",
				  // size: "small",
				  buttons: {
				    cancel: {
				      label: "Cancel",
				      className: "btn-default btn-sm",
				      callback: function() {
				        
				      }
				    },
				    success: {
				      label: "Confirm",
				      className: "btn-primary btn-sm",
				      callback: function() {

				    	// $('.content-block.active-block').remove();
				    	$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');
						$('.active-block').removeClass('active-block');

				      }
				    },
				  }
				});
				
			});



			$('#preview-summary-'+ block_id).on('click', function (e) {
				e.preventDefault();
				console.log( $wrapper.data() );

				// MSPB2.getSummary({
				// 	type: $this.data('type'),
				// 	blog_id: $this.data('blog-id'),
				// 	no_of_items: $this.data('no-of-items'),
				// 	show_title: $this.data('show-title'),
				// 	show_thumbnail: $this.data('show-thumbnail'),
				// 	show_excerpt: $this.data('show-excerpt'),
				// 	show_read_more_link: $this.data('show-read-more-link'),
				// 	wall_column_width: $this.data('wall-items-per-row'),
				// 	wall_gutter_width: $this.data('wall-gutter-width'),
				// 	header_text: $this.data('header-text'),
				// 	car_aspect_ratio: $this.data('car-aspect-ratio'),
				// 	car_items_per_row: $this.data('car-items-per-row'),
				// 	list_aspect_ratio: $this.data('list-aspect-ratio'),
				// 	list_image_size: $this.data('list-image-size'),
				// 	list_image_alignment: $this.data('list-image-alignment'),
				// 	grid_aspect_ratio: $this.data('grid-aspect-ratio'),
				// 	grid_items_per_row: $this.data('grid-items-per-row'),
				// 	grid_gutter_width: $this.data('grid-gutter-width'),
				// 	text_size: $this.data('text-size'),
				// 	text_alignment: $this.data('text-alignment'),
				// 	metadata_position: $this.data('metadata-position'),
				// }, function (data) {
				// 	console.log(data);
				// })

			});

		
		},

		getImageAspectRatio: function(aspect_ratio) {
			
			switch (aspect_ratio) {
		 		case 'Auto':
		 			return "ar-auto";
		 			break;
		 		case '1:1 Square':
		 			return "ar-square";
		 			break;
		 		case '3:2 Standard':
		 			return "ar-standard";
		 			break;
		 		case '2:3 Standard (Vertical)':
		 			return "ar-standard-vertical";
		 			break;
		 		case '4:3 Four-Three':
		 			return "ar-four-three";
		 			break;
		 		case '3:4 Three-Four (Vertical)':
		 			return "ar-three-four-vertical";
		 			break;
		 		case '16:9 Widescreen':
		 			return "ar-widescreen";
		 			break;
		 		case '2.40 Anamorphic Widescreen':
		 			return "ar-anomorphic-widescreen";
		 			break;
		 		default:
		 			return '';
		 			break;
		 	}

		},

		clearImageAspectRatioClass: function(wrapper) {
				wrapper.removeClass('ar-auto');
				wrapper.removeClass('ar-square');
				wrapper.removeClass('ar-standard');
				wrapper.removeClass('ar-standard-vertical');
				wrapper.removeClass('ar-four-three');
				wrapper.removeClass('ar-three-four-vertical');
				wrapper.removeClass('ar-widescreen');
				wrapper.removeClass('ar-anomorphic-widescreen');
		},

		getSummary: function (params, callback) {
		
			return $.ajax({
				url: base_url + 'page/get_blog',
				type: 'POST',
				dataType: 'json',
				data: {param1: 'value1'},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(callback);

		},

		videoDialog: function (videoID, media_id) {
			
			if ( getFormQuery == 1 ) return;
			getFormQuery = 1;

			$.ajax({
				url: base_url + 'cms/pages/get_form',
				type: 'POST',
				dataType: 'json',
				data: {
					form: 'video_block',
					media_id: media_id,
				},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				getFormQuery = 0;
				if ( data.is_valid ) {
					// console.log(data.form)
					
					$('#msdialog').unbind();
					$('#msdialog').msdialog({
						title: "Video",
						content: data.form,
						height: $(window).height() - 200,
						width: 400,
						is_visible: null,
						position: {
							right: '20px',
							bottom: '60px',
						},
						footer: '<div class="form-inline">'+
									// '<div class="form-group right10">'+
									// 	'<a id="cancel-video-'+videoID+'" class="btn btn-sm btn-default cancel-block">Cancel</a>'+
									// '</div>'+	
									'<div class="form-group">'+
										'<a id="save-video-'+videoID+'" class="btn btn-sm btn-primary close-active-block-btn">Dismiss</a>'+
									'</div>'+
								'</div>',
						created: function () {
							setTimeout(function () {
								$('#msdialog').find('.main-dialog-wrapper').addClass('visible')
							}, 100)

							$('#video_url').focus().val($('.active-block').data('video-url'));
							
							// console.log("created")
							MSPB2.newImageUploader(videoID);

							$(".main-dialog-wrapper").draggable({
							    handle: ".dialog-header",
							});	

							MSPB2.videoBlockSettings(videoID);
							
						}

					});



				};
				console.log("complete");
			});

		},

		videoBlockSettings: function (videoID) {
			var currentVideoURL;

			$('#video_url').on('change', function (e) {
				var $this = $(this);

				if ( currentVideoURL != $this.val() ) {
					currentVideoURL = $this.val();
					MSPB2.getVideoID(videoID, $this.val());	
				};
				
			});

			$('#save-video-'+videoID).on('click', function (e) {
				e.preventDefault();
				$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');

				if ( $('.active-block').find('.content-block-controls').find('.edit-block').length == 0 ) {
					$('.active-block').find('.content-block-controls').prepend('<a data-id="'+videoID+'" class="btn btn-default edit-block" href="#">Edit</a>');
				};
				$('.active-block').removeClass('active-block');
			});

			$('#cancel-video-'+videoID).on('click', function (e) {
				e.preventDefault();

				bootbox.dialog({
				  message: "Are you sure you wish to remove this block?",
				  title: "Remove Block Confirmation",
				  // size: "small",
				  buttons: {
				    cancel: {
				      label: "Cancel",
				      className: "btn-default btn-sm",
				      callback: function() {
				        
				      }
				    },
				    success: {
				      label: "Confirm",
				      className: "btn-primary btn-sm",
				      callback: function() {

				    	// $('.content-block.active-block').remove();
				    	$('#msdialog').find('.main-dialog-wrapper').removeClass('visible');
						$('.active-block').removeClass('active-block');

				      }
				    },
				  }
				});
				
			});



			$('#custom_thumbnail').live('change', function (e) {
				var $this = $(this);
				if ( $this.is(':checked') ) {
					$('.active-block').data('custom-thumbnail', 1);
				}else{
					$('.active-block').data('custom-thumbnail', 0);
				}
			});
			
			$('#video_caption').live('change', function () {
				// console.log($(this).val())
				$('.active-block').data('video-caption', $(this).val());
			});


			if ( $('.active-block').data('video-caption') ) {
				$('#video_caption').val( $('.active-block').data('video-caption') );
			};
			if ( $('.active-block').data('custom-thumbnail') ) {
				$('#custom_thumbnail').prop('checked', 'checked');
			};
		},

		getVideoID: function (videoID, url) {

			$('.video-url-message').text("Fetching Video URL...");
			$.ajax({
				url: 'https://noembed.com/embed',
				type: 'POST',
				dataType: 'jsonp',
				data: {url: url},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				
				if (typeof data.provider_name != "undefined" ) {
					
					$('.active-block').data('video-url', url);

					console.log( $('.active-block').data('video-url') )

					if ( data.provider_name == "YouTube" ) {
						var src = $(data.html).attr('src');
						console.log(src)
						var iframe = '<iframe class="embed-responsive-item" width="560" height="315" src="'+src+'" frameborder="0" allowfullscreen></iframe>';
						$('.active-block').find('.video-block-wrapper').html( iframe );
					};

					if ( data.provider_name == "Vimeo" ) {
						var src = $(data.html).attr('src');
						var iframe = '<iframe class="embed-responsive-item" width="560" height="315" src="'+src+'" frameborder="0" allowfullscreen></iframe>';
						$('.active-block').find('.video-block-wrapper').html( iframe );
					};

					if ( data.provider_name == "Facebook" ) {						
						$('.active-block').find('.video-block-wrapper').html( data.html );
					};


					$('.video-url-message').text("Successfully Located.");
				}else{
					$('.video-url-message').text("URL is not embeddable.");
				}

				console.log("complete");
			});
				
		},

		contentTiny: function (id, auto_focus) {	

			tinymce.init({
				plugins: "autoresize, link, paste",
				paste_text_sticky : true,
				setup: function (ed) {
					console.log(ed)	
			
					ed.on('focus', function(e) {
						$('.active-block').removeClass('active-block');
						tinymce.activeEditor.$('p').parents('.content-block').addClass('active-block')
						tinymce.activeEditor.$('p').parents('.content-block-wrap').addClass('focus-state')
						// console.log()
						var curContent = tinymce.activeEditor.getContent({format: 'text'})
						if ( $.trim(curContent) == "Write here..." ) {
							tinymce.activeEditor.setContent('');
						};
			        });

			        ed.on('blur', function () {
			        	$('.active-block').removeClass('active-block');

			        	ed.$('p').parents('.content-block-wrap').removeClass('focus-state')
			        	if ( $.trim(ed.getContent()) == "" ) {
			        		tinymce.activeEditor.setContent('<p class="text-muted">Write here...</p>');	
			        	}

			        });




					ed.on('change', function(e) {
			            // console.log('change event', e);
			            // ed.execCommand('mceAutoResize');
			        });

			        // ed.on('ObjectSelected', function(e) {
			        //     console.log('ObjectSelected event', e);
			        // });

			        // ed.on('NodeChange', function(e) {
			        // 	// console.log("NodeChange")
			        // 	// console.log(e)
			        // 	var offset = $(e.element).position();
			        // 	var $currentParentWrap = $(e.element).parents('div');

			        // 	tinymceData.currentParentWrap = $currentParentWrap.data('type');
			        // 	// console.log(tinymceData)
			        //     console.log(offset);

			        //     $('.add-content-wrap').addClass('fadeIn').css({
			        //     	top: offset.top - 10,
			        //     	left: -80,
			        //     });

			        // });



				},
			    selector: id,
			    auto_focus: auto_focus,
			    inline: true,
			    // fixed_toolbar_container: "#mytoolbar",
			    // plugins: [],
			    menubar: false,
			    theme: "modern",
			    skin: 'light',
			    height: 500,
			    autoresize_min_height: 500,
			    toolbar: "insertfile undo redo | bold italic link alignleft aligncenter alignright alignjustify | styleselect | bullist numlist outdent indent | pastetext removeformat"
			});
			
			$(id).addClass('tinymce-added').parents('.content-block');
			// tinymce.execCommand('mceFocus',false,id);

		},

		openContentElementWrap: function (btn) {
			
			btn.addClass('active');

			var offset = btn.find('.tiny-add-icon').offset();
			// var offset = btn.find('.tiny-add-icon').position();

			console.log(offset)
			var posY = offset.top;
			var posX = 20;

			// $('#content-blocks-wrap').addClass('open').css(btn.position());
			$('#content-blocks-wrap').addClass('open');

			// .css({
			// 	top: 20,
			// 	left: 15,
			// });

			setTimeout(function () {
				$('#content-blocks-wrap').addClass('go-animated');
				$('.content-element-search').focus();
			}, 200);

		},

		closeContentElementWrap: function () {
			$('.tiny-add-content.active').removeClass('active');
			$('#content-blocks-wrap').removeClass('go-animated');
			setTimeout(function () {
				$('#content-blocks-wrap').removeClass('open');
			}, 200);

		},

		prepareContentBlock: function () {
			
			var contentBlocks = [];

			contentBlocks.push({
				name: "parent",
				value: currentURL.query.id,
			});

			$.each($('.content-block'), function(index, val) {
				
				var $this = $(this);

				contentBlocks.push({
					name: "type[]",
					value: $this.data('type'),
				});

				// var wrap_class = '';
				// if ( $this.data('wrap_class') ) {
				// 	wrap_class = $this.data('wrap_class');
				// }
				// if ( $this.data('wrap-class') == null && $this.parents('.content-block-col').hasClass('content-block-col') ) {
				// 	wrap_class = '';
				// }

				contentBlocks.push({
					name: "wrap_class[]",
					value: ($this.data('wrap-class')) ? $this.data('wrap-class') : 'col-sm-12',
				});

				contentBlocks.push({
					name: "content_block[]",
					value: index,
				});

				

				var contentID = (typeof $this.data('content-id') != "undefined") ? $this.data('content-id') : 0;
				contentBlocks.push({
					name: "content_id[]",
					value: contentID,
				});

				if ( $this.data('type') == "Paragraph" ) {
					var paraID = $this.find('.content-block-editor').attr('id');
					if ( $('#'+paraID).hasClass('tinymce-added') ) {
						contentBlocks.push({
							name: "body[]",
							value: tinymce.get(paraID).getContent(),
						});	
					};
					
				};

				if ( $this.data('type') == "Quote" ) {
					var blockquote = $this.find('.blockquote-figure').html();
					contentBlocks.push({
						name: "body[]",
						value: blockquote,
					});						
				};

				if ( $this.data('type') == "Image" ) {
					
					contentBlocks.push({
						name: "media_id[]",
						value: $this.data('media-id'),
					});
					contentBlocks.push({
						name: "body[]",
						value: $this.data('media-id'),
					});
					contentBlocks.push({
						name: "path[]",
						value: $this.data('path'),
					});
					contentBlocks.push({
						name: "file_name[]",
						value: $this.data('file-name'),
					});
					contentBlocks.push({
						name: "image_caption_placement[]",
						value: $this.data('caption-placement'),
					});
					contentBlocks.push({
						name: "image_caption[]",
						value: $this.data('image-caption'),
					});
					contentBlocks.push({
						name: "stretch[]",
						value: $this.data('stretch'),
					});
					contentBlocks.push({
						name: "lightbox[]",
						value: $this.data('lightbox'),
					});
					contentBlocks.push({
						name: "url[]",
						value: $this.data('url'),
					});
					contentBlocks.push({
						name: "open_in_new_window[]",
						value: $this.data('new-window'),
					});
				}else{
					contentBlocks.push({name: "path[]", value: null, });
					contentBlocks.push({name: "file_name[]", value: null, });
					contentBlocks.push({name: "image_caption[]", value: null, });
					contentBlocks.push({name: "image_caption_placement[]", value: null, });
					contentBlocks.push({name: "stretch[]", value: null, });
					contentBlocks.push({name: "lightbox[]", value: null, });
					contentBlocks.push({name: "url[]", value: null, });
					contentBlocks.push({name: "open_in_new_window[]", value: null, });
				}


				if ( $this.data('type') == "Video" ) {
					
					contentBlocks.push({
						name: "body[]",
						value: $this.find('.video-block-wrapper').html(),
					});
					contentBlocks.push({
						name: "video_url[]",
						value: $this.data('video-url'),
					});
					contentBlocks.push({
						name: "media_id[]",
						value: $this.data('media-id'),
					});
					contentBlocks.push({
						name: "custom_thumbnail[]",
						value: $this.data('custom-thumbnail'),
					});
					contentBlocks.push({
						name: "video_caption[]",
						value: $this.data('video-caption'),
					});
				}else{
					contentBlocks.push({name: "video_url[]", value: null, });

					contentBlocks.push({name: "media_id[]", value: null, });

					contentBlocks.push({name: "custom_thumbnail[]", value: null, });

					contentBlocks.push({name: "video_caption[]", value: null, });
				}

				if ( $this.data('type') == "Embed" ) {
					
					contentBlocks.push({
						name: "body[]",
						value: $this.find('.embed-block-wrapper').data('html'),
					});
					contentBlocks.push({
						name: "alignment[]",
						value: $this.find('.embed-block-wrapper').data('alignment'),
					});
				}else{
					contentBlocks.push({name: "alignment[]", value: null, });

				}

				if ( $this.data('type') == "Summary Wall" || $this.data('type') == "Summary Carousel" || $this.data('type') == "Summary List" || $this.data('type') == "Summary Grid" ) {

					var $summaryContainer = $this.find('.msis-summary-container');

					contentBlocks.push({
						name: "body[]",
						value: $summaryContainer.data('blog-id'),
					});

					contentBlocks.push({
						name: "no_of_items[]",
						value: $summaryContainer.data('no-of-items'),
					});

					contentBlocks.push({
						name: "summary_full_width[]",
						value: $summaryContainer.data('summary-full-width'),
					});

					contentBlocks.push({
						name: "show_title[]",
						value: $summaryContainer.data('show-title'),
					});

					contentBlocks.push({
						name: "show_thumbnail[]",
						value: $summaryContainer.data('show-thumbnail'),
					});

					contentBlocks.push({
						name: "show_excerpt[]",
						value: $summaryContainer.data('show-excerpt'),
					});

					contentBlocks.push({
						name: "show_read_more_link[]",
						value: $summaryContainer.data('show-read-more-link'),
					});

					contentBlocks.push({
						name: "featured_filter[]",
						value: $summaryContainer.data('featured-filter'),
					});

					contentBlocks.push({
						name: "wall_items_per_row[]",
						value: $summaryContainer.data('wall-items-per-row'),
					});

					contentBlocks.push({
						name: "wall_gutter_width[]",
						value: $summaryContainer.data('wall-gutter-width'),
					});

					contentBlocks.push({
						name: "header_text[]",
						value: $summaryContainer.data('header-text'),
					});

					contentBlocks.push({
						name: "car_aspect_ratio[]",
						value: $summaryContainer.data('car-aspect-ratio'),
					});

					contentBlocks.push({
						name: "car_items_per_row[]",
						value: $summaryContainer.data('car-items-per-row'),
					});

					contentBlocks.push({
						name: "list_aspect_ratio[]",
						value: $summaryContainer.data('list-aspect-ratio'),
					});

					contentBlocks.push({
						name: "list_image_size[]",
						value: $summaryContainer.data('list-image-size'),
					});

					contentBlocks.push({
						name: "list_image_alignment[]",
						value: $summaryContainer.data('list-image-alignment'),
					});

					contentBlocks.push({
						name: "grid_aspect_ratio[]",
						value: $summaryContainer.data('grid-aspect-ratio'),
					});

					contentBlocks.push({
						name: "grid_items_per_row[]",
						value: $summaryContainer.data('grid-items-per-row'),
					});

					contentBlocks.push({
						name: "grid_gutter_width[]",
						value: $summaryContainer.data('grid-gutter-width'),
					});

					contentBlocks.push({
						name: "text_size[]",
						value: $summaryContainer.data('text-size'),
					});

					contentBlocks.push({
						name: "text_alignment[]",
						value: $summaryContainer.data('text-alignment'),
					});

					contentBlocks.push({
						name: "metadata_position[]",
						value: $summaryContainer.data('metadata-position'),
					});

				}else{
					contentBlocks.push({name: "no_of_items[]", value: null, });
					contentBlocks.push({name: "summary_full_width[]", value: null, });
					contentBlocks.push({name: "show_title[]", value: null, });
					contentBlocks.push({name: "show_thumbnail[]", value: null, });
					contentBlocks.push({name: "show_excerpt[]", value: null, });
					contentBlocks.push({name: "show_read_more_link[]", value: null, });
					contentBlocks.push({name: "featured_filter[]", value: null, });
					contentBlocks.push({name: "wall_items_per_row[]", value: null, });
					contentBlocks.push({name: "wall_gutter_width[]", value: null, });
					contentBlocks.push({name: "header_text[]", value: null, });
					contentBlocks.push({name: "car_aspect_ratio[]", value: null, });
					contentBlocks.push({name: "car_items_per_row[]", value: null, });
					contentBlocks.push({name: "list_aspect_ratio[]", value: null, });
					contentBlocks.push({name: "list_image_size[]", value: null, });
					contentBlocks.push({name: "list_image_alignment[]", value: null, });
					contentBlocks.push({name: "grid_aspect_ratio[]", value: null, });
					contentBlocks.push({name: "grid_items_per_row[]", value: null, });
					contentBlocks.push({name: "grid_gutter_width[]", value: null, });
					contentBlocks.push({name: "text_size[]", value: null, });
					contentBlocks.push({name: "text_alignment[]", value: null, });
					contentBlocks.push({name: "metadata_position[]", value: null, });
				}





				if ( $this.data('type') !="Video" || $this.data('type') !="Image" ) {
					// Image
					// Video
				};

				if ( $this.data('type') == "Spacer" ) {
				
					contentBlocks.push({
						name: "body[]",
						value: $this.find('.spacer-block-wrapper').html(),
					});		
				};

			});

			console.log(contentBlocks)

			return contentBlocks;

		},

		saveContent: function () {
			$('.prepare-content-btn').on('click', function (e) {
				e.preventDefault();
				MSPB2.submitContent(function(data) {
					is_query = 0;
					console.log(data)
					if ( data.is_valid ) {
						location.reload();
					};
					

					console.log("complete");
				});

			});
		},

		submitContent: function (callback) {

			if ( is_query ) return;

			var content = MSPB2.prepareContentBlock();

			is_query = 1;

			$.ajax({
				url: base_url + 'cms/pages/validate_content_block',
				type: 'POST',
				dataType: 'json',
				data: content,
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(callback);
				

		},





	};

	MSPB2.init();

});		



