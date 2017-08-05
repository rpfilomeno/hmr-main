jQuery(document).ready(function($) {
	
	var base_url = $('#base_url').val()
	,	$win = $(window)
	,	currentURL = new Url
	,	main = {
		init: function () {
			this.bindEvents()
			this.lazyload()
			this.timeago()
			this.bootstrapPlugins()
			this.msis()
			this.landingSlider()
			this.featuredProductSlider()
			this.search()
			//this.featuredBlocks()
			this.blogMasonry()
			this.eventSignupForm()
			this.contactForm()
			this.instagramFeed()
			this.mainNav()
			this.newsLetterForm()
			// this.ever()
			
		},

		featuredProductSlider: function() {
		
			if ( $(".featured-product-carousel").length == 0 ) return;

			$.each($(".featured-product-carousel"), function(index, val) {
				var $this = $(this);

				var owl = $this.owlCarousel({
					lazyLoad: true,
					stagePadding: 0,
					nav: true,
					// loop: true,
					autoplay: 0,
					autoplaySpeed: 1000,
					autoplayHoverPause: true,
					navSpeed : 1000,
					dots: false,
					margin: 10,
					items: 3,
					// center: true,
					responsiveClass:true,
					mouseDrag: false,
				    responsive:{
				        0:{
				            items:1,
				            nav:true
				        },
				        768:{
				            items:3,
				            // nav:false
				        },
				        1200:{
				            items: 3,
				            nav:true,
				            loop:false
				        }
				    },
				    navText: [
				      "<i class='ion-ios-arrow-left'></i>",
				      "<i class='ion-ios-arrow-right'></i>"
				    ],
				    onInitialized: function() {
				    	main.toggleArrows($this);
				    },
				    // animateOut: 'slideOutDown',
				    // animateIn: 'flipInX',
				});

			});

            

		},

		newsLetterForm: function() {
		
			$('#newsletter-form').on('submit', function(e) {
				e.preventDefault();
				var $this = $(this);

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
					if ( typeof data !="undefined" && data.is_valid ) {
						bootbox.dialog({
							title: "Thank you!", 
							message: "<p><strong>Thank you for subscribing to our newsletter!</strong></p><p>Please check your inbox to confirm your subscription.</p>",
							buttons: {
								dismiss: {
							      label: "Dismiss",
							      className: "btn-sm btn-default",
							      callback: function () {
							      	// location.reload();
							      }
							    }
							}
						});

						$this.find('.form-control').val('');
					}else{
						$this.find('.form-group').removeClass('has-error');
						$this.find('.error-help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$this.find("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
			            	}
			            });
					}
					console.log("complete");
				});
					

			});

		},

		ever: function() {
			
			var rdata = {"game":[{"score":49000,"commonEarned":49000,"premiumEarned":0,"energyEarned":0,"playerType":"fiona","kittenPair":"FC10:FC12","kittenCount":2,"averageFPS":36.19323431274338,"sessionGameCount":0,"secondsElapsed":7,"killedBy":"Monster 1","background":"ocean","timestamp":"2017-05-28T04:09:10.535Z","version":"5.1.9","sessionID":"e0ebc248-fe8a-46f8-a6ba-dbc389575ea3","userID":"1288344654616342","deepLink":"","ip_address":"${keen.ip}","user_agent":"${keen.user_agent}","OS_TYPE":"Mac OS X","OS_VERSION":"10.12.1","APP_RUNTIME":"browser","DEVICE_TYPE":"desktop","BROWSER_TYPE":"Chrome","BROWSER_VERSION":"58.0.3029.110","SIMULATED":false,"keen":{"addons":[{"name":"keen:ip_to_geo","input":{"ip":"ip_address"},"output":"ip_geo_info"},{"name":"keen:ua_parser","input":{"ua_string":"user_agent"},"output":"parsed_user_agent"},{"name":"keen:date_time_parser","input":{"date_time":"timestamp"},"output":"timestamp_info"}]},"userProperties":{"sessionCount":64,"gamesPlayed":261,"coins":156947,"xp":115847,"level":17,"contextID":"1471248919598202","contextCount":11,"inviteCount":0,"firstEntrySourceType":"UPDATE","firstEntrySourceUID":"SCREENSHOT_PLAYING","firstEntrySourcePlayerID":"1468850586500801","firstEntrySourceContextID":"1471248919598202","contextType":"THREAD","entrySourceType":"UPDATE","entrySourceUID":"SCREENSHOT_PLAYING","lowResEnabled":false,"version":"5.1.9","entrySourcePlayerID":"1288344654616342","entrySourceContextID":"1471248919598202","sourceAB_missionScripts":"control","playerID":"1288344654616342","abTest_missionScripts":"control"},"abTestVariants":{"missionScripts":{"testName":"Mission Scripts 5/4/17","testID":"missionScripts","testVariantName":"Control Group (1x Choose)","testVariantID":"control"}}}],"updateAsyncSuccess":[{"playerID":"1288344654616342","contextID":"1471248919598202","timestamp":"2017-05-28T04:09:13.988Z","screenshotType":"SCREENSHOT_PLAYING","deepLink":"","sourceType":"UPDATE","sourceUID":"SCREENSHOT_PLAYING","push":"NO_PUSH","sourceAB_missionScripts":"control","version":"5.1.9","sessionID":"e0ebc248-fe8a-46f8-a6ba-dbc389575ea3","userID":"1288344654616342","ip_address":"${keen.ip}","user_agent":"${keen.user_agent}","OS_TYPE":"Mac OS X","OS_VERSION":"10.12.1","APP_RUNTIME":"browser","DEVICE_TYPE":"desktop","BROWSER_TYPE":"Chrome","BROWSER_VERSION":"58.0.3029.110","SIMULATED":false,"keen":{"addons":[{"name":"keen:ip_to_geo","input":{"ip":"ip_address"},"output":"ip_geo_info"},{"name":"keen:ua_parser","input":{"ua_string":"user_agent"},"output":"parsed_user_agent"},{"name":"keen:date_time_parser","input":{"date_time":"timestamp"},"output":"timestamp_info"}]},"userProperties":{"sessionCount":64,"gamesPlayed":261,"coins":156947,"xp":115847,"level":17,"contextID":"1471248919598202","contextCount":11,"inviteCount":0,"firstEntrySourceType":"UPDATE","firstEntrySourceUID":"SCREENSHOT_PLAYING","firstEntrySourcePlayerID":"1468850586500801","firstEntrySourceContextID":"1471248919598202","contextType":"THREAD","entrySourceType":"UPDATE","entrySourceUID":"SCREENSHOT_PLAYING","lowResEnabled":false,"version":"5.1.9","entrySourcePlayerID":"1288344654616342","entrySourceContextID":"1471248919598202","sourceAB_missionScripts":"control","playerID":"1288344654616342","abTest_missionScripts":"control"},"abTestVariants":{"missionScripts":{"testName":"Mission Scripts 5/4/17","testID":"missionScripts","testVariantName":"Control Group (1x Choose)","testVariantID":"control"}}}]}
			// var rdata = {"game":[{"score":50000,"commonEarned":49000,"premiumEarned":5000,"energyEarned":3000,"playerType":"coin","kittenPair":"FC10:FC12","kittenCount":2,"averageFPS":57.90826060652739,"sessionGameCount":0,"secondsElapsed":7,"killedBy":"Monster 1","background":"desert","timestamp":"2017-05-28T03:44:25.337Z","version":"5.1.9","sessionID":"c0d9b1f6-34dc-4a2c-a49c-882abbf1b5d9","userID":"1288344654616342","deepLink":"","ip_address":"${keen.ip}","user_agent":"${keen.user_agent}","OS_TYPE":"Mac OS X","OS_VERSION":"10.12.1","APP_RUNTIME":"browser","DEVICE_TYPE":"desktop","BROWSER_TYPE":"Chrome","BROWSER_VERSION":"58.0.3029.110","SIMULATED":false,"keen":{"addons":[{"name":"keen:ip_to_geo","input":{"ip":"ip_address"},"output":"ip_geo_info"},{"name":"keen:ua_parser","input":{"ua_string":"user_agent"},"output":"parsed_user_agent"},{"name":"keen:date_time_parser","input":{"date_time":"timestamp"},"output":"timestamp_info"}]},"userProperties":{"inviteCount":0,"firstEntrySourceType":"UPDATE","firstEntrySourceUID":"SCREENSHOT_PLAYING","firstEntrySourcePlayerID":"1468850586500801","firstEntrySourceContextID":"1471248919598202","contextID":"1471248919598202","contextType":"THREAD","entrySourceType":"UPDATE","entrySourceUID":"SCREENSHOT_FRIEND_PASSED","sessionCount":61,"gamesPlayed":257,"coins":999999,"xp":315783,"level":30,"contextCount":11,"lowResEnabled":false,"version":"5.1.9","entrySourcePlayerID":"1288344654616342","entrySourceContextID":"1471248919598202","sourceAB_missionScripts":"control","playerID":"1288344654616342","abTest_missionScripts":"control"},"abTestVariants":{"missionScripts":{"testName":"Mission Scripts 5/4/17","testID":"missionScripts","testVariantName":"Control Group (1x Choose)","testVariantID":"control"}}}]}

			$.ajax({
				url: 'https://blackstorm-api.keen.io/3.0/projects/57d0b4528db53dfda8a6f107/events?api_key=508ECFE50136F32598ABB43E0E388137CE4E27E6F51A98743FFF59C562C46567609E8C5FC6E9D79408F5FEE3B9B314AE915C12EFA9AED4EF31556B8F0E656B3692CA55CE615EF733210ACB18C0D4AFCAE7CC44CA80F455332A69D985BF7AF56E',
				type: 'POST',
			    contentType: "application/json",
				dataType: 'json',
				data: JSON.stringify(rdata)
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				console.log("complete");
			});
				

		},

		ever2: function() {
			
			
			var rdata = {
				client: '6d6d033b2cd6318f6970c502e5dca07d',
				e: [{"device_id":"070cbcc6-7111-41a1-88f0-7d910a33308bR","user_id":null,"timestamp":1495943625421,"event_id":139,"session_id":1495939007748,"event_type":"$identify","version_name":null,"platform":"Web","os_name":"Chrome","os_version":"58","device_model":"Mac","language":"en-US","api_properties":{},"event_properties":{},"user_properties":{"$set":{"sessionCount":63,"gamesPlayed":259,"coins":456915,"xp":515802,"level":30,"contextID":"1471248919598202","contextCount":11,"inviteCount":0,"firstEntrySourceType":"UPDATE","firstEntrySourceUID":"SCREENSHOT_PLAYING","firstEntrySourcePlayerID":"1468850586500801","firstEntrySourceContextID":"1471248919598202","contextType":"THREAD","entrySourceType":"UPDATE","entrySourceUID":"SCREENSHOT_PLAYING","lowResEnabled":false,"version":"5.1.9","entrySourcePlayerID":"1288344654616342","entrySourceContextID":"1471248919598202","sourceAB_missionScripts":"control","playerID":"1288344654616342","abTest_missionScripts":"control"}},"uuid":"1999475b-2627-43fc-a3c4-256fbb9c0694","library":{"name":"amplitude-js","version":"3.2.0"},"sequence_number":589,"groups":{},"user_agent":"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36"}],
				v:2
			}

			$.ajax({
				url: 'https://api.amplitude.com/',
				type: 'POST',
			    contentType: "application/json",
				dataType: 'jsonp',
				data: JSON.stringify(rdata)
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				console.log("complete");
			});
				

		},
		
		landingSlider: function() {

            if ( $("#landing-slider").length == 0 ) return;
            var $landingSlider = $("#landing-slider").owlCarousel({
                items: 1,
                loop: 1,
                center: 1,
                mouseDrag: 0,
                touchDrag: 0,
                // autoWidth: 1,
                dots: 1,
                nav: 1,
                smartSpeed: 850,
                navText: [''],
                autoplay: 1,
                dotsData: true,

                callbacks: true,
                onInitialize: function(event) {
                    // $('#landing-slider .image.wh').removeClass('wh')
                    // console.log("initialize")
                },
                responsive : {
				    1680 : {
				    	width: 1500
				        // stagePadding: 100,
		          //       margin: 150,
				    }
				}
            });
                
        },

		minTwoDigits: function(n) {
			 return (n < 10 ? '0' : '') + n;	
		},
		
		mainNav: function () {
			
			var $mainNavItems = $('#main-nav-items');
			var $mobileNavWrapper = $('#mobile-nav-wrapper');
			var $mobileNavInject = $('#mobile-nav-inject');

			if ( $.trim($mobileNavInject.html()).length == 0 ) {
				$mobileNavInject.html( $mainNavItems.html() );	
			}
			


			$('#nav-trigger').on('click touchend', function(e) {
				var $this = $(this);
				e.preventDefault();
		        e.stopPropagation();
				$('body').toggleClass('nav-open');

				$mobileNavWrapper.toggleClass('open-nav');
				
			});	


			var $navTrigger = $('#nav-trigger');

			$(window).on('scroll', main.debouncer( function ( e ) {
			    
			    if ( $(window).scrollTop() > 700 ) {
			    	$navTrigger.addClass('black-nav')
			    }else{
			    	$navTrigger.removeClass('black-nav')
			    }

			}));

			
		},

		contactForm: function() {
		
			var $talkBtn = $('#talk-submit-btn');

			$('#inquiries-form').on('submit', function(e) {
				e.preventDefault();
				var $this = $(this);

				$talkBtn.prop('disabled', 1);

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

					$talkBtn.prop('disabled', 0);
					if ( typeof data !="undefined" && data.is_valid ) {
						bootbox.dialog({
							title: "Email sent!", 
							message: "<p><strong>Thank you for contacting!</strong></p><p>I’ll get back to you as soon as possible! :)</p>",
							buttons: {
								dismiss: {
							      label: "Dismiss",
							      className: "btn-sm btn-default",
							      callback: function () {
							      	// location.reload();
							      }
							    }
							}
						});

						$this.find('.form-control').val('');
					}else{
						$this.find('.form-group').removeClass('has-error');
						$this.find('.error-help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$this.find("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
			            	}
			            });
					}
					console.log("complete");
				});
						

			});

		},

		instagramFeed: function() {
		
		
			var $instagram = $('.instagram-inject');
			var $IGWrap = $('.instagram-wrap');
			var $IGUsername = $('#ig-username').text();

			if ( $IGUsername.length == 0 ) return;

			$.ajax({
				url: base_url + 'q/get_ig_feed',
				type: 'GET',
				dataType: 'json',
				data: {
					username: $IGUsername,
				},
			})
			.fail(function(e) {
				// console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				// console.log(data)
				
				if ( typeof data.items !="undefined" ) {
					$IGWrap.removeClass('hide');

					$.each($('.instagram-inject'), function(i, vall) {
						var $this = $(this)

						$.each(data.items, function(index, ig) {
							if ( index < $this.data('count') ) {
								$this.append('<li>'
												+'<a target="_BLANK" href="'+ ig.link +'"><img data-original="'+ ig.images.standard_resolution.url +'" class="img-responsive lazy"></a>'
											+'</li>');
							}
						});
					});

					main.lazyload();

					
				}

				// console.log("complete");
			});
			

		},

		eventSignupForm: function() {
		
			var $eventSignupForm = $('#signup-event-form');
			var $eventFormMsg = $('#event-form-message');
			var $btnSubmitEvent = $('#btn-submit-event-form');

			$eventSignupForm.on('submit', function(e) {
				e.preventDefault();
				var $this = $(this);

				$btnSubmitEvent.prop('disabled', 1);

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

					$btnSubmitEvent.prop('disabled', 0);
					
					if ( typeof data !="undefined" && data.is_valid ) {
						$this.addClass('hide');

						$eventFormMsg.removeClass('hide')
									 .html('<h3>Thank you!</h3><p>We will send an email once your chair has been confirmed!</p>');
					}else{

						$this.find('.form-group').removeClass('has-error');
						$this.find('.error-help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$this.find("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
			            	}
			            });

					}

					console.log("complete");
				});

			});



			$('.join-btn-submit').on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				var arrData = $('#signup-event-form').serializeArray();

				$this.prop('disabled', 1);

				$.ajax({
					url: base_url + 'q/v_join',
					type: 'POST',
					dataType: 'json',
					data: arrData,
				})
				.fail(function(e) {
					console.log(e.responseText)
					console.log("error");
				})
				.always(function(data) {

					$this.prop('disabled', 0);
					
					if ( typeof data !="undefined" && data.is_valid ) {
						$eventSignupForm.addClass('hide');

						$eventFormMsg.removeClass('hide')
									 .html('<h3>Thank you!</h3><p>We will send an email once your chair has been confirmed!</p>');
					}else{

						$eventSignupForm.find('.form-group').removeClass('has-error');
						$eventSignupForm.find('.error-help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$eventSignupForm.find("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
			            	}
			            });

					}

				});
					

			});

		},

		blogMasonry: function() {
			
			var $blogPostsMasonry = $('#item-posts-masonry');
			var gutter = 30;

			if ( $blogPostsMasonry.length == 0 ) return;

			$blogPostsMasonry.find('.grid-item').css({
				'padding-left': gutter / 2,
				'padding-right': gutter / 2,
				'margin-bottom': gutter,
			});

			$blogPostsMasonry.css({
				'margin-left': -(gutter / 2),
				'margin-right': -(gutter / 2),
			});

			var $grid = $blogPostsMasonry.masonry({
				transitionDuration: 100,
				itemSelector: '.grid-item',
				percentPosition: true,
			});

			$(window).resize( main.debouncer( function ( e ) {
			    $blogPostsMasonry.masonry('bindResize');
			}));
			
			$blogPostsMasonry.masonry('layout');

			

			
			


		},

		featuredBlocks: function() {
		
			if ( $('#featured-blocks').length == 0 ) return;
        	
        	var $featuredBlocks = $('#featured-blocks').masonry({
			  itemSelector: '.grid-item',
			  columnWidth: '.grid-sizer',
			  percentPosition: true,
			  // gutter: 15,
			  // isFitWidth: true,
			});

			$.each($('#featured-blocks > li'), function(index, val) {
				var $this = $(this);

				setTimeout(function() {
					$this.addClass('fadeIn');
				}, 100 * index)

			});


		},

		bindEvents: function () {

			if ( $('.share-btn').length !=0 ) {
				$('.share-btn').MSSocial();	
			}
			
			$('a[data-toggle="tab"]').on('click', function (e) {
				e.preventDefault();
			});


			main.windowResize();

		},

		windowResize: function() {
			

			var $winWidth = $('.win-w');
			var $winHeight = $('.win-h');

			$winWidth.width( $win.width() )
		    $winHeight.width( $win.height() )
			
			$(window).resize( main.debouncer( function ( e ) {
			    console.log("FIRE RESIZE!")
			    $winWidth.width( $win.width() )
			    $winHeight.width( $win.height() )

			}));

		},


		productList: function () {
			


		},
		lazyload: function () {
			
			if(!jQuery().lazyload) {
				$(".lazy").removeClass('lazy');
				return;
			}

			// console.log("lazy")
			$.each($('.lazy'), function(i, val) {
				var $this = $(this);
				$this.show().lazyload({ 
				    effect: "fadeIn",
				    effectspeed: 1000,
				}).removeClass("lazy");
			});

			$(document).ajaxStop(function(){
			    $.each( $('.lazy') , function(index, val) {
			    	var $this = $(this);
			    	setTimeout(function () {
			    		$this.show().lazyload({ 
					        effect: "fadeIn",
					        effectspeed: 1000,
					    }).removeClass("lazy");
			    	}, 50 * index);
			    });
			});

			$('.modal').live('shown.bs.modal', function () {
				$(".modal img").show().lazyload().trigger("lazyload");
				console.log("shown modal")
			});

		},

		summaryBlock: function () {
			
			$.each($('.summary-block'), function(index, val) {
				var $this = $(this);
				// if (  ) {};

			});			

		},

		msis: function () {
		
			var $msisContainer = $('.msis-summary-container');
	    	if ( $msisContainer.length == 0 ) return;

	    	$.each( $('.msis-summary-container') , function(index, val) {
	    		var $this = $(this);
	    		// console.log($this.data())
	    		$this.msis({
					ajaxOptions:{
						data: {
							ajax: 1,
							author_id: $this.data('author-id'),
							blog_id: $this.data('blog-id'),
							limit: $this.data('no-of-items'),
							type: $this.data('type'),
							tpl: $this.data('tpl'),
							ob: $this.data('ob'),
							order_by: $this.data('order-by'),
							url_query: currentURL.query,


							content_id: $this.data('content-id'),
							show_title: $this.data('show-title'),
							show_thumbnail: $this.data('show-thumbnail'),
							show_excerpt: $this.data('show-excerpt'),
							show_read_more_link: $this.data('show-read-more-link'),
							featured_filter: $this.data('featured-filter'),

							summary_full_width: $this.data('summary-full-width'),
							wall_column_width: $this.data('wall-column-width'),
							wall_gutter_width: $this.data('wall-gutter-width'),
							header_text: $this.data('header-text'),
							car_aspect_ratio: $this.data('car-aspect-ratio'),
							car_items_per_row: $this.data('car-items-per-row'),

							list_aspect_ratio: $this.data('list-aspect-ratio'),
							list_image_size: $this.data('list-image-size'),
							list_image_alignment: $this.data('list-image-alignment'),

							grid_aspect_ratio: $this.data('grid-aspect-ratio'),
							grid_column_width: $this.data('grid-column-width'),
							grid_gutter_width: $this.data('grid-gutter-width'),
							text_size: $this.data('text-size'),
							text_alignment: $this.data('text-alignment'),
							metadata_position: $this.data('metadata-position'),
							most_popular: $this.data('most-popular'),
							except_id: $this.data('except-ids'),
						}
					},
					errorMessage: "",
					// errorClass: "text-muted dash-left-pad top20",
					loader_image: base_url + 'img/loader.gif',
					// trigger_type: "button",
					// load_more_class: "btn btn-default",

					created: function (el) {
							
					},
					responseError: function (e, el, response) {
						console.log(response.responseText)
					},
					done: function (e, el, response) {
						// console.log(e);
						// console.log(el);
						// console.log($this.data())
						// console.log($this.data('type'))
						// console.log($this.attr('id'))

						// console.log(response);
						if ( response.is_valid ) {

							$this.append(response.result_html);

							if ( $this.data('type') == "Summary Wall" ) {
								var gutter = parseInt($this.data('wall-gutter-width'));
								if ( gutter !=0 ) {
									$('body').css('overflow-x', 'hidden');
								};

								$this.css({
									'margin-left': -(gutter / 2),
									'margin-right': -(gutter / 2),
								});

								var givenPerRow = parseInt($this.data('wall-items-per-row'));
								var itemsPerRow = 4;

								if ( givenPerRow == 2 ) {
									itemsPerRow = 6;
									// $this.find('.grid-item').removeClass('col-sm-4');
								};

								if ( givenPerRow == 3 ) {
									itemsPerRow = 4;
								};
								if ( givenPerRow == 4 ) {
									itemsPerRow = 3;
								};
								if ( givenPerRow == 6 ) {
									itemsPerRow = 2;
								};
								
								console.log(itemsPerRow)
								console.log(itemsPerRow)

								$this.find('.grid-item').addClass('col-md-'+ itemsPerRow).css({
									'padding-left': gutter / 2,
									'padding-right': gutter / 2,
									'margin-bottom': gutter,

									// 'width': $this.data('wall-column-width'),
								});

								var $grid = $this.masonry({
									transitionDuration: 0,
									itemSelector: '.grid-item',
									columnWidth: '.grid-sizer',
									percentPosition: true,
								});

								$(window).resize( main.debouncer( function ( e ) {
								    $this.masonry('bindResize');
								}));

								// $grid.masonry('layout');

								// console.log($this.data('wall-gutter-width'))
							};

							if ( $this.data('type') == "Summary Carousel" ) {
								var owl = $this.owlCarousel({
									stagePadding: 0,
									nav: true,
									// loop: true,
									autoplay: true,
									autoplaySpeed: 1000,
									autoplayHoverPause: true,
									navSpeed : 1000,
									dots: false,
									margin: 20,
									items: $this.data('car-items-per-row'),
									// center: true,
									responsiveClass:true,
									mouseDrag: false,
								    responsive:{
								        0:{
								            items:1,
								            nav:true
								        },
								        768:{
								            items:3,
								            // nav:false
								        },
								        1200:{
								            items: $this.data('car-items-per-row'),
								            nav:true,
								            loop:false
								        }
								    },
								    navText: [
								      "<i class='ion-ios-arrow-left'></i>",
								      "<i class='ion-ios-arrow-right'></i>"
								    ],
								    onInitialized: function() {
								    	main.toggleArrows($this);
								    },
								    // animateOut: 'slideOutDown',
								    // animateIn: 'flipInX',
								});


					            
					            owl.on('translated.owl.carousel', function (event) { main.toggleArrows($this); });

								// owl.on('translated.owl.carousel', function(event) {
								//     var index = event.item.index;

								//     console.log(event)
								//     console.log(event.item.count)
								//     console.log("items-per-row:" + $this.data('car-items-per-row'))

								//     // if ( index == 0 ) {
								//     // 	$this.find('.owl-prev').addClass('off-control');
								//     // }else{
								//     // 	$this.find('.owl-prev').removeClass('off-control');
								//     // }



								// })
							};


							if ( $this.data('type') == "Summary Grid" ) {

								main.setMasonryHeight()
								
								

								var gutter = parseInt($this.data('grid-gutter-width'));
								if ( gutter !=0 ) {
									$('body').css('overflow-x', 'hidden');
								};

								$this.css({
									'margin-left': -(gutter / 2),
									'margin-right': -(gutter / 2),
								});

								var givenPerRow = parseInt($this.data('grid-items-per-row'));
								var itemsPerRow = 4;

								if ( givenPerRow == 3 ) {
									itemsPerRow = 4;
								};
								if ( givenPerRow == 4 ) {
									itemsPerRow = 3;
								};
								if ( givenPerRow == 6 ) {
									itemsPerRow = 2;
								};
								

								$this.find('.grid-item').addClass('col-md-'+ itemsPerRow).css({
									'padding-left': gutter / 2,
									'padding-right': gutter / 2,
									'margin-bottom': gutter,

									// 'width': $this.data('wall-column-width'),
								});

								var $grid = $this.masonry({
								  itemSelector: '.grid-item',
								  // columnWidth: $this.data('wall-column-width'),
								  columnWidth: '.grid-sizer',
								  percentPosition: true,
								  transitionDuration: 0,
								  // gutter: parseInt($this.data('wall-gutter-width')),
								});

								$(window).resize( main.debouncer( function ( e ) {
								    main.setMasonryHeight()	

								    $this.masonry('bindResize')
								}));

								
								main.setMasonryHeight()	
								$grid.masonry('layout');

								$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
									main.setMasonryHeight()	
									$grid.masonry('layout');
								});

								// console.log($this.data('wall-gutter-width'))
							};

							$('.share-btn').MSSocial();



						};
					}
				});
	    	});

		},

		toggleArrows: function(elem) {
		
			if( elem.find(".owl-item").last().hasClass('active') && elem.find(".owl-item.active").index() == elem.find(".owl-item").first().index() ){
                elem.find('.owl-nav .owl-next').addClass("off-control");
                elem.find('.owl-nav .owl-prev').addClass("off-control"); 
            }
            //disable next
            else if(elem.find(".owl-item").last().hasClass('active')){
                elem.find('.owl-nav .owl-next').addClass("off-control");
                elem.find('.owl-nav .owl-prev').removeClass("off-control"); 
            }
            //disable previus
            else if(elem.find(".owl-item.active").index() == elem.find(".owl-item").first().index()) {
                elem.find('.owl-nav .owl-next').removeClass("off-control"); 
                elem.find('.owl-nav .owl-prev').addClass("off-control");
            }
            else{
                elem.find('.owl-nav .owl-next,.owl-nav .owl-prev').removeClass("off-control");  
            }

		},

		setMasonryHeight: function() {
			
			var $list = $( '.msis-summary-container.summary-grid' )
			,	$items = $list.find( '.grid-item' )
	       
	        $items.css( 'height', 'auto' );
	 
            var perRow = Math.floor( $list.width() / $items.width() );

            if( perRow == null || perRow < 2 ) return true;
 
            for( var i = 0, j = $items.length; i < j; i += perRow ){
                var maxHeight   = 0,
                    $row        = $items.slice( i, i + perRow );
 
                $row.each( function(){
                    var itemHeight = parseInt( $( this ).outerHeight() );
                    if ( itemHeight > maxHeight ) maxHeight = itemHeight;
                });

                $row.css( 'height', maxHeight );
            }


		},

		timeago: function () {
		
			if(!jQuery().timeago) return;

			// $.timeago.settings.strings.suffixAgo = "";
			// $.timeago.settings.strings.hours = "%d hours";

			$("time.timeago").timeago();

		},

		particle: function () {
			
			if ( $('#particles-js').length == 0 ) return;

		    particlesJS.load('particles-js', base_url + 'js/vendor/particlesjs-config.json', function() {
		        // console.log('callback - particles.js config loaded');
		    });
		},

		search: function() {
			
			var $searchDialog = $('#search-dialog');
			var $searchBtn = $('.search-dialog-btn');
			var $searchInput = $('#search-input');

			$searchBtn.on('click', function(e) {
				e.preventDefault()
				if ( $searchDialog.hasClass('hide') ) {
					$searchDialog.removeClass('hide');
				}
				$('body').addClass('ms-dialog-open');

				$searchInput.focus();
			});

			$('.ms-dialog-close-btn').on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				$this.parents('.ms-dialog').addClass('hide');
				$('body').removeClass('ms-dialog-open');
			});

			$(document).keyup(function(e) {
			  if (e.keyCode == 27){
			  	if ( $('body').hasClass('ms-dialog-open') ) {
			  		$('.ms-dialog').addClass('hide');
			  		$('body').removeClass('ms-dialog-open');
			  	}
			  }
			});


			$('#search-form').on('submit', function(e) {
				e.preventDefault();

				// currentURL.query.s = $searchInput.val();

				window.location = base_url + '?s=' + $searchInput.val();
			});
		},

		bootstrapPlugins: function () {
	    	$('[data-toggle="tooltip"]').tooltip();

	    },	

	    debouncer: function(func, timeout) {
	    	 var timeoutID , timeout = timeout || 400;
			   return function () {
			      var scope = this , args = arguments;
			      clearTimeout( timeoutID );
			      timeoutID = setTimeout( function () {
			          func.apply( scope , Array.prototype.slice.call( args ) );
			      } , timeout );
			   }
	    }


	};

	main.init();

});		






