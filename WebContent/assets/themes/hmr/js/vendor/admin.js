var MSAdmin;

jQuery(document).ready(function($) {
		
	var base_url = $('#base_url').val()
	,	$dashMainContainer = $('#dash-main-container')
	,	$dashPageNav = $('#pages-navigation-wrap')
	,	currentURL = new Url;
	MSAdmin = {
		init: function () {
			// this.history();
			this.bindEvents();
			this.addUser();
			this.addPolitician();
			this.msis();
			this.nav();
			this.bootstrapPlugins();
			this.searchForm();
			this.selectize();
			this.sortableList();
			this.customerSubscription();
		},
		bindEvents: function () {

			MSAdmin.pagePreloader();
			MSAdmin.lazyload();
			MSAdmin.queryLink();
			MSAdmin.windowOnResize();
			MSAdmin.windowHeight();
			MSAdmin.tinyMCE();
			MSAdmin.autosizeTextArea();
			MSAdmin.markAsPaid();
			webArtJs.hideMeShowThis();
		},

		markAsPaid: function() {
		
			$('.mark-as-paid').on('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				bootbox.dialog({
				  title: "Confirmation",
				  message: "Are you sure you wish to update this payment status?",
				  // size: "small",
				  buttons: {
				    dismiss: {
				      label: "Dismiss",
				      className: "btn-default btn-sm",
				      callback: function() {
				      	
				      }
				    },
				    confirm: {
				      label: "Confirm",
				      className: "btn-primary btn-sm",
				      callback: function() {
				      	
				      	MSAdmin.submitPayment($this.data('id'), $this.data('event-id'), $this.data('customer-id'), $this.data('status'));

				      }
				    },
				  }
				});
			});


			$('.payment-failed').on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				bootbox.dialog({
				  title: "Confirmation",
				  message: "Are you sure you wish to send payment failed email?",
				  // size: "small",
				  buttons: {
				    dismiss: {
				      label: "Dismiss",
				      className: "btn-default btn-sm",
				      callback: function() {
				      	
				      }
				    },
				    confirm: {
				      label: "Confirm",
				      className: "btn-primary btn-sm",
				      callback: function() {
				      	
				      	MSAdmin.submitPaymentFailed($this.data('event-id'), $this.data('customer-id'), $this.data('id') );

				      }
				    },
				  }
				});
			});


			$('.fully-booked').on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				bootbox.dialog({
				  title: "Confirmation",
				  message: "Are you sure you wish to send fully booked email?",
				  // size: "small",
				  buttons: {
				    dismiss: {
				      label: "Dismiss",
				      className: "btn-default btn-sm",
				      callback: function() {
				      	
				      }
				    },
				    confirm: {
				      label: "Confirm",
				      className: "btn-primary btn-sm",
				      callback: function() {
				      	
				      	MSAdmin.submitFullyBooked($this.data('event-id'), $this.data('customer-id'), $this.data('id') );

				      }
				    },
				  }
				});
			});

		},

		submitPayment: function(id, event_id, customer_id, status) {
		
			$.ajax({
				url: base_url + 'cms/customers/mark_as_paid',
				type: 'POST',
				dataType: 'json',
				data: {
					id: id,
					event_id: event_id,
					customer_id: customer_id,
					status: status,
				},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				if ( typeof data !="undefined" && data.is_valid ) {

					bootbox.dialog({
					  title: "Congratulations!",
					  message: "Customer's payment status has been updated.",
					  // size: "small",
					  buttons: {
					    dismiss: {
					      label: "Dismiss",
					      className: "btn-default btn-sm",
					      callback: function() {
					      	location.reload();  
					      }
					    },
					  }
					});

				}
				console.log("complete");
			});

		},

		submitPaymentFailed: function(event_id, customer_id, id) {
		
			$.ajax({
				url: base_url + 'cms/customers/payment_failed',
				type: 'POST',
				dataType: 'json',
				data: {
					id: id,
					event_id: event_id,
					customer_id: customer_id,
				},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				if ( typeof data !="undefined" && data.is_valid ) {

					bootbox.dialog({
					  title: "Congratulations!",
					  message: "Payment failed email has been sent.",
					  // size: "small",
					  buttons: {
					    dismiss: {
					      label: "Dismiss",
					      className: "btn-default btn-sm",
					      callback: function() {
					      	location.reload(); 
					      }
					    },
					  }
					});

				}
				console.log("complete");
			});

		},

		submitFullyBooked: function(event_id, customer_id, id) {
		
			$.ajax({
				url: base_url + 'cms/customers/fully_booked',
				type: 'POST',
				dataType: 'json',
				data: {
					id: id,
					event_id: event_id,
					customer_id: customer_id,
				},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				if ( typeof data !="undefined" && data.is_valid ) {

					bootbox.dialog({
					  title: "Congratulations!",
					  message: "Fully booked email has been sent.",
					  // size: "small",
					  buttons: {
					    dismiss: {
					      label: "Dismiss",
					      className: "btn-default btn-sm",
					      callback: function() {
					      	location.reload(); 
					      }
					    },
					  }
					});

				}
				console.log("complete");
			});

		},

		customerSubscription: function() {
		
			if ( $('#confirm-customer-payment-form').length == 0 ) return;

			var confirm_form_is_query = 0;

			$('#confirm-customer-payment-form').on('submit', function(e) {
				e.preventDefault();
				var $this = $(this);

				if ( confirm_form_is_query == 1 ) return;
				confirm_form_is_query = 1;
				$this.find('.btn-submit').prop('disabled', 1);

				$.ajax({
					url: $this.attr('action'),
					type: 'POST',
					dataType: 'json',
					data: $this.serializeArray(),
				})
				.fail(function(e) {
					console.log(e.responseText)
					console.log("error");
				})
				.always(function(data) {
					console.log(data)
					confirm_form_is_query = 0;

					$this.find('.btn-submit').prop('disabled', 0);

					if ( typeof data !="undefined" && data.is_valid ) {
						bootbox.dialog({
							title: "Congratulations!", 
							message: "<p>Subscription has been saved.</p><p>Confirmation email has been sent to "+ data.email +"</p>",
							buttons: {
								dismiss: {
							      label: "Dismiss",
							      className: "btn-sm btn-default",
							      callback: function () {
							      	location.reload();
							      }
							    }
							}
						});
					}

					if ( typeof data !="undefined" && !data.is_valid ) {
						webArtJs.messenger({
							header: 'Error occurs!',
							message: 'Please check all the required fields',
							closeAfter: 3000,
							position: "small top-left",
							closeBtn: true,
						});

						$('.form-group').removeClass('has-error');
						$('.error-help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
			            	}
			            });
					}


					console.log("complete");
				});
						

			});

		},

		pagePreloader: function () {
			$( document ).ajaxSend(function() {
				$('.page-preloader').addClass('active');
			});
			$( document ).ajaxComplete(function() {
				$('.page-preloader').removeClass('active');
			});
		},

		sortableList: function() {
			
			if ( $('.sortable-list').length == 0 ) return;

			$.each($('.sortable-list'), function(index, val) {
				var list = document.getElementById($(this).attr('id'));
				
				if ( list ) {
					Sortable.create(list, { /* options */ });
				}
			});

		},

		windowOnResize: function () {
			
			$(window).on('resize', function () {
				MSAdmin.windowHeight();
			});

		},

		windowHeight: function () {
			// console.log($(window).height());

			// console.log($('.wh').length)
			$.each( $('.wh'), function(index, val) {
				var $this = $(this);
				var offset = ($this.data('offset')) ? parseInt($this.data('offset')) : 0;

				$this.height($(window).height() - offset);
			});
		},
		
		lazyload: function () {
			
			if(!jQuery().lazyload) {
				$("img.lazy").removeClass('lazy');
				return;
			}

			console.log("lazy")
			$("img.lazy").lazyload({ 
			    effect: "fadeIn",
			    effectspeed: 1000,
			}).removeClass("lazy");

			$(document).ajaxStop(function(){
			    $.each( $('img.lazy') , function(index, val) {
			    	var $this = $(this);
			    	setTimeout(function () {
			    		$this.lazyload({ 
					        effect: "fadeIn",
					        effectspeed: 1000,
					    }).removeClass("lazy");
			    	}, 50 * index);
			    });
			});

			$('.modal').live('shown.bs.modal', function () {
				$(".modal img").lazyload().trigger("lazyload");
				console.log("shown modal")
			});

			setTimeout(function () {
				$(window).scroll();
				// console.log("scroll")
			}, 400);
		},
		forms: function () {

		},
		searchForm: function () {
			$('.search-form').on('submit', function (e) {
				e.preventDefault();
			 	var serializeArray = $(this).serializeArray()
				currentURL.query.q = serializeArray[0].value;
				if ( serializeArray[0].value == '' ) {
					delete currentURL.query.q;
				};
				window.location = currentURL.toString();
			});
		},
		addUser: function () {

			$userform = $('#add-user-form');

			var options = { 
		        beforeSubmit:  function () {
		        	console.log("beforeSubmit")
		        },
		        data:{
		        	ajax: 1,
		        },
		        success: function (data) {
		        	console.log(data)
		        	if ( data.is_valid ) { 
				        // webArtJs.iftd(data.id, 'works'); 

				        if ( data.id !=0 && data.step == 1 ) {
				        	bootbox.dialog({
								title: "Congratulations!",
								message: "Post has been saved.",
								buttons: {
									dismiss: {
								      label: "Dismiss",
								      className: "btn-sm btn-default",
								      callback: function () {
								      	window.location = base_url + 'cms/users/profile/' + data.id;
								      }
								    },
								    save: {
								      label: "Set Profile Picture",
								      className: "btn-sm btn-primary",
								      callback: function() {
								        window.location = base_url + 'cms/users/edit?id=' + data.id + '&step=2';
								        return false;
								      }
								    }
								}
							})
				        };

				        if ( data.id !=0 && data.step == 2 ) {
				        	window.location = base_url + 'cms/users/add?id=' + data.id + '&step=3';
				        };

				        if ( data.id !=0 && data.step == 3 ) {
				        	bootbox.dialog({
								title: "Congratulations!", 
								message: "User account has been saved.",
								buttons: {
									dismiss: {
								      label: "Dismiss",
								      className: "btn-sm btn-default",
								      callback: function () {
								      	window.location = base_url + 'cms/users/profile/' + data.id;
								      }
								    }
								}
							});
				        };
				       
				    }else{
				    	webArtJs.messenger({
							header: 'Error occurs!',
							message: 'Please check all the required fields',
							closeAfter: 3000,
							position: "small top-left",
							closeBtn: true,
						});

						$('.form-group').removeClass('has-error');
						$('.error-help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
			            	}
			            });
				    }
		        },
		        error: function (e) {
		        	console.log(e.responseText)
		        },
		        dataType: 'json',
		        // clearForm: true,
		        // resetForm: true,
		    }; 
			 
		    $userform.submit(function() {
		        $(this).ajaxSubmit(options); 
		        return false; 
		    }); 

	   		
	    },

	    addPolitician: function () {

			$politicianform = $('#add-politician-form');

			var options = { 
		        beforeSubmit:  function () {
		        	console.log("beforeSubmit")
		        },
		        data:{
		        	ajax: 1,
		        },
		        success: function (data) {
		        	console.log(data)
		        	if ( data.is_valid ) { 
				        // webArtJs.iftd(data.id, 'works'); 

				        if ( data.id !=0 && data.step == 1 ) {
				        	bootbox.dialog({
								title: "Congratulations!",
								message: "Post has been saved.",
								buttons: {
									dismiss: {
								      label: "Dismiss",
								      className: "btn-sm btn-default",
								      callback: function () {
								      	window.location = base_url + 'cms/politicians/profile/' + data.id;
								      }
								    },
								    save: {
								      label: "Set Profile Picture",
								      className: "btn-sm btn-primary",
								      callback: function() {
								        window.location = base_url + 'cms/politicians/edit?id=' + data.id + '&step=2';
								        return false;
								      }
								    }
								}
							})
				        };

				        if ( data.id !=0 && data.step == 2 ) {
				        	// window.location = base_url + 'cms/politicians/add?id=' + data.id + '&step=3';
				        	window.location = base_url + 'cms/politicians/profile/' + data.id;
				        };
				       
				    }else{
				    	webArtJs.messenger({
							header: 'Error occurs!',
							message: 'Please check all the required fields',
							closeAfter: 3000,
							position: "small top-left",
							closeBtn: true,
						});

						$('.form-group').removeClass('has-error');
						$('.error-help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
			            	}
			            });
				    }
		        },
		        error: function (e) {
		        	console.log(e.responseText)
		        },
		        dataType: 'json',
		        // clearForm: true,
		        // resetForm: true,
		    }; 
			 
		    $politicianform.submit(function() {
		        $(this).ajaxSubmit(options); 
		        return false; 
		    }); 

	   		
	    },
	    doPost: function ($form, params, callback) {

	      if ( $form.length != 0 ) {  
	        console.log(params)
	        if ( typeof tinyMCE !='undefined' ) {
	          tinyMCE.triggerSave();  
	        };
	        
	        if ( typeof params != "undefined" && params.is_draft ) {
	          $('#is_draft').attr('checked', true);
	        }else{
	          $('#is_draft').attr('checked', false);
	        }

	        if ( $form.hasClass('submitting') ) return;

	        $form.addClass('submitting');
	        
	        $.ajax({
	          url: $form.attr('action'),
	          type: 'POST',
	          dataType: 'json',
	          data: $form.serializeArray(),
	        })
	        .done(callback)
	        .fail(function(e) {
	          console.log(e.responseText)
	          console.log("error");
	        })
	        .always(function(data) {
	          console.log(data);
	          $form.removeClass('submitting');
	          if ( data.is_valid ) {      
	            
	            $('.form-group').removeClass('has-error').find('.help-block').text("");       

	            if ( $form.find('#edit_mode').length == 0 && $form.find('#id').length == 0 ) {
	                $form.append('<input type="hidden" name="edit_mode" id="edit_mode" value="true">').append('<input type="hidden" id="id" name="id" value='+data.id+'>');
	            };  
	            
	          }else{
	           
				webArtJs.messenger({
					header: 'Error occurs!',
					message: 'Please check all the required fields',
					closeAfter: 3000,
					position: "small top-left",
					closeBtn: true,
				});

	            $.each(data, function(index, val) {
	              if (val !="") {
	                $("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val);
	              }else{
	                $("#" + index).parents('.form-group').removeClass('has-error').find('.help-block').text("");
	              }
	            });
	          }
	        });
	      };      
	    },


	    nav: function () {
	    	
	    	$('#toggle-dash-menu').on('click', function (e) {
	    		e.preventDefault();
	    		var $this = $(this);
	    		$this.blur();
	    		$this.parents('li').toggleClass('active');
	    		$('body').toggleClass('dmc-open');
	    		
	    	});

	    	$.each($('.sequence'), function(i, val) {
	    		var $this = $(this);
	    		
	    		setTimeout(function() {
	    			setTimeout(function() {
		    			$this.addClass('fadeInRight');
		    		}, 80 * i);
	    		}, 200);
	    	});

	    },

	    history: function() {
	    	
	    	 // we get a normal Location object

	        /*
	         * Note, this is the only difference when using this library,
	         * because the object window.location cannot be overriden,
	         * so library the returns generated "location" object within
	         * an object window.history, so get it out of "history.location".
	         * For browsers supporting "history.pushState" get generated
	         * object "location" with the usual "window.location".
	         */
	        var location = window.history.location || window.location;

	        // looking for all the links and hang on the event, all references in this document
	        $(document).on('click', 'a.ajax', function(e) {
	        	var $this = $(this);
	        	e.preventDefault()
	          // keep the link in the browser history
	          history.pushState(null, null, $this.attr('href'));
	          

	          MSAdmin.getPage( $this.attr('href') );
	          // here can cause data loading, etc.

	          // do not give a default action
	          
	        });

	        // hang on popstate event triggered by pressing back/forward in browser
	        $(window).on('popstate', function(e) {

	          // here can cause data loading, etc.

	          // just post
	          alert("We returned to the page with a link: " + location.href);
	        });


	    },

	    getPage: function(url) {
	    
	    	$.ajax({
	    		url: url,
	    		type: 'GET',
	    		dataType: 'json',
	    		data: {ajax: 1},
	    	})
	    	.fail(function(e) {
	    		console.log(e.responseText)
	    		console.log("error");
	    	})
	    	.always(function(data) {
	    		console.log(data)
	    		if ( typeof data != "undefined" && data.is_valid ) {
	    			
	    		}
	    		console.log("complete");
	    	});
	    
	    },

	    bootstrapPlugins: function () {
	    	$('[data-toggle="tooltip"]').tooltip();
	    },

	    queryLink: function () {
	    	
	    	$('.query-link').live('click', function (e) {
	    		e.preventDefault();
	    		var $this = $(this);

	    		if ( $this.data('query') && $this.data('value') ) {
	    			currentURL.query[$this.data('query')] = $this.data('value');
		    		window.location = currentURL.toString();
	    		};
	    	});
	    },

	    msis: function () {
	    	
	    	$msisContainer = $('.msis-container');
	    	if ( $msisContainer.length == 0 ) return;

	    	$.each( $('.msis-container') , function(index, val) {
	    		var $this = $(this);
	    		$msisContainer.msis({
					ajaxOptions:{
						data: {
							ajax: 1,
							tpl: $this.data('tpl'),
							status: $this.data('status'),
							q: $this.data('q'),
							url_query: currentURL.query,
						}
					},
					errorClass: "text-muted dash-left-pad top20",
					loader_image: base_url + 'img/loader.gif',
					trigger_type: $this.data('trigger-type'), //"button"
					// load_more_class: "btn btn-default",

					created: function (el) {
							
					},
					responseError: function (e, el, response) {
						console.log(response.responseText)
					},
					done: function (e, el, response) {
						// console.log(e);
						// console.log(el);
						console.log(response);
						// console.log("doooonnnee");
						if ( typeof response != "undefined" && typeof response.total_rows_formatted != "undefined" ) {
							$('.msis-count').text(response.total_rows_formatted);
							if ( response.total_rows == 1 && $this.data('label') != null ) {
								$('.header-label').text( $this.data('label') )
							}
							if ( response.total_rows > 1 && $this.data('label-plural') != null ) {
								$('.header-label').text( $this.data('label-plural') )
							}
						};

						if ( typeof currentURL.query.reorder != "undefined" ) {
							MSAdmin.doSort();
						};

						
					},
					done_append: function(e, el, response) {
						MSAdmin.timeago()
						MSAdmin.bootstrapPlugins()
					}
				});
	    	});

	    },

	    timeago: function () {
		
			if(!jQuery().timeago) return;

			// $.timeago.settings.strings.suffixAgo = "";
			// $.timeago.settings.strings.hours = "%d hours";
			console.log("timeago")
			$("time.timeago").timeago();

		},
	    
	    doSort: function () {

	      if ( typeof $.fn.nestedSortable == 'undefined' ) return;

	      if ( $('.sortable').length == 0 ) return;

	      console.log("doSort")
	      $('.sortable').nestedSortable({
	          handle: 'div',
	          items: 'li.dash-sortable',
	          toleranceElement: '> div.dash-stream',
	          maxLevels: 1,
	          change: function () {
	            
	          }
	      }); 

	      MSAdmin.saveOrder();
	    },

	    saveOrder: function () {
	    
	      $('.save-order-btn').on('click', function (e) {
	        var $this = $(this);
	        var curText = $this.text();
	        olArray = $('.sortable').nestedSortable('toArray');
	        console.log( olArray )
	        $this.text("Saving...");
	        if ( !$this.hasClass('submitting') ) {
	          $this.addClass('submitting');
	          $.ajax({
	            url: $this.data('save-url'),
	            type: 'POST',
	            dataType: 'json',
	            data: {
	              pages: olArray,
	              tbl_name: $this.data('tbl-name'),
	            },
	          })
	          .fail(function(e) {
	            console.log(e.responseText)
	            console.log("error");
	          })
	          .always(function(data) {
	            console.log(data)
	            $this.text(curText);
	            if ( data.is_valid ) {
	              $this.removeClass('submitting');
	              webArtJs.messenger({
	                header: 'Heads Up!',
	                message: 'Order has been saved.',
	                closeAfter: 3000,
	                position: "small top-left",
	                closeBtn: true,
	              });
	            };
	            console.log("complete");
	          });
	        };
	        
	        
	        e.preventDefault();
	      });

	    },

	    tinyMCE: function () {
	    	
	    	$.each($('.ms-tinymce'), function(index, val) {
	    		var $this = $(this);
	    		var id = '#' + $this.attr('id');
	    		var auto_focus = ($this.data('auto-focus')) ? $this.data('auto-focus') : false;
	    		var height = ($this.data('height')) ? $this.data('height') : 150;
	    		var toolbar = ( $this.data('toolbar') ) ? $this.data('toolbar') : "undo redo | bold italic link | styleselect |  bullist numlist outdent indent | pastetext removeformat fullscreen";
	    		if ( id !=null ) {

	    			tinymce.init({
						plugins: "autoresize, link, paste, fullscreen, placeholder",
				        // theme_advanced_buttons3_add : "pastetext,pasteword,selectall",
						content_css: base_url + 'css/tinymce.css',
						style_formats: [
					        {title: "Paragraph", format: "p"},
					        {title: "Heading 1", format: "h1"},
					        {title: "Heading 2", format: "h2"},
					        {title: "Heading 3", format: "h3"},
					        {title: "Quote", format: "blockquote"},
					        {title: "Pre", format: "pre"}
					    ],
						setup: function (ed) {
							ed.on('change', function () {
					            tinymce.triggerSave();
					        });
						},
					    selector: id,
					    // auto_focus: auto_focus,
					    // inline: true,
					    // fixed_toolbar_container: "#mytoolbar",
					    // plugins: [],
					    menubar: false,	
					    // theme: "modern",
					    skin: 'light',
					    height: height,
					    autoresize_min_height: height,
					    statusbar: false,
					    toolbar: toolbar,
					    // alignleft aligncenter alignright alignjustify
					});
					
					$(id).addClass('tinymce-added').parents('.content-block');

	    		};

	    	});

	    	

	    },

	    autosizeTextArea: function() {
	    	

	    	if ( $('.autosize-ta').length !=0 ) {
	    		autosize($('.autosize-ta'));
	    	}

	    },

	    selectize: function () {
	    	
	    	if ( $('.ms-selectize').length == 0 || !jQuery().selectize ) return;

	    	$.each( $('.ms-selectize') , function(index, val) {
	    		var $this = $(this);
	    		var options = {
					create: true,
					createOnBlur: true,
				};

				if ( $this.data('plugins') ) {
					var plugins = $this.data('plugins').split(',');
					console.log(plugins);
					options.plugins = plugins;
				};

				var $select = $('#'+ $this.attr('id')).selectize(options);

	    	});
	    
	    	
	    },
	    
	    isValidSelector: function(selector) {
	    	
	    	 try {
		        var $element = $(selector);
		    } catch(error) {
		        return false;
		    }
		    return true;

	    },


	};

	MSAdmin.init();

});	