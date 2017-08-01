jQuery(document).ready(function($) {



	var successCallback, errorCallback, tokenRequest;
	var $customerRegisterForm = $('#customers-register-form');
	var $registerModal = $('#register-modal');

	var base_url = $('#base_url').val()
	,	currentURL = new Url
	,	politicks_base_price = 99
	,	plus_price = 30000
	,	premium_price = 50000
	,	unit_price = politicks_base_price
	,	pulseWall = null
	,	is_query = 0
	,	main = {
		init: function () {
			this.bindEvents()
			this.lazyload()
			this.bootstrapPlugins()
			this.verticalCenter()
			this.windowHeight()
			this.windowOnResize()
			
			this.mainNav()
			this.appPreviewSlider()
			this.planSlider()
			this.feedBackModal()
			this.parallaxBG()
			this.smooth()
			this.signUp()
			this.videoBG()
		},

		videoBG: function() {
		
			// Politicks-Premium-BG.mp4
		

			if ( $('#customer-login-wrap').length !=0 ) {
				var BV = new $.BigVideo();

			    if (!Modernizr.touch) {
				    BV.init();
				    BV.show( base_url + 'uploads/Politicks-Premium-BG.mp4', { doLoop:true } );
				}		

			}

			
			

		},

		appPreviewSlider: function() {
		
			if ( $("#app-preview-carousel").length == 0 ) return;
            var lastVideoId = '';

            $landingSlider = $("#app-preview-carousel").owlCarousel({
                items: 1,
                margin: 0,
                loop: 1,
                autoplay: 1,
                dots: 0,
                nav: true,
                lazyLoad: 1,
                navText: ['<svg width="100%" height="100%" viewBox="0 0 11 20"><path style="fill:none;stroke-width: 1px;stroke: #ffffff;" d="M9.554,1.001l-8.607,8.607l8.607,8.606"/></svg>','<svg width="100%" height="100%" viewBox="0 0 11 20" version="1.1"><path style="fill:none;stroke-width: 1px;stroke: #ffffff;" d="M1.054,18.214l8.606,-8.606l-8.606,-8.607"/></svg>'],
                navContainer: '#app-preview-nav',
            });
          

		},

		signUp: function() {

			var planStr = "Politicks";
			var $creditCardPayment = $('#credit-card-payment');
			var $localBankPayment = $('#local-bank-payment');
			var $TrialOption = $('#trial-payment');

			var $creditFormWrap = $('#credit-card-form-wrap');
			var $localBankFormWrap = $('#local-bank-form-wrap');

			var $plan = $('#politicks_plan');
			var $planSelect = $('.plan-card-select');
			var $registerBtn = $('#register-btn');
			var $localBankPaymentWrap = $('#local-bank-payment-wrap');
			var $trialPaymentWrap = $('#trial-payment-wrap');

			var $item_input = $('#item');
			var $unit_price_input = $('#unit_price');
			var $no_of_months = $('#no_of_months');
			var $total_summary = $('#total_summary');
			var $no_of_months_summary = $('#no_of_months_summary');
			var $item_summary = $('#item_summary');

			if ( $total_summary.length !=0 ) {
				$total_summary.text( accounting.formatMoney(unit_price, "P ") );	
			}
			
			if ( $unit_price_input.length !=0 ) {
				$unit_price_input.val( accounting.formatMoney(unit_price, "P ") );	
			}
			

			

	        $no_of_months.on('change', function(e) {
	           var $this = $(this);
	           if ( $this.val() == 0 ) {
	           	$this.val(1);
	           }

	           $no_of_months_summary.text( $this.val() );
	           var numberOfMonths = parseInt($this.val());
	           var total = unit_price * numberOfMonths;

	           $no_of_months_summary.text( $this.val() )
	           $total_summary.text( accounting.formatMoney(total, "P ") );

	        });



	        if ( $plan.val() == "Politicks" ) {
				unit_price = politicks_base_price;
				planStr = "Politicks";
				$localBankPaymentWrap.addClass('hide');
			}else{
				$localBankPaymentWrap.removeClass('hide');
			}

	        if ( $plan.val() == "Plus" ) {
	        	unit_price = plus_price;
				planStr = "Politicks Plus";
	        }

	        if ( $plan.val() == "Premium" ) {
				unit_price = premium_price;
				planStr = "Politicks Premium";
			}

			$item_input.val( planStr );
			$item_summary.text( planStr );

			var numberOfMonths = parseInt($no_of_months.val());
			var total = unit_price * numberOfMonths;

			$no_of_months_summary.text( $no_of_months.val() );
			// $total_summary.text( total + '.00');
			if ( $total_summary.length !=0 ) {
				$total_summary.text( accounting.formatMoney(total, "P ") );	
			}
			if ( $unit_price_input.length !=0 ) {
				$unit_price_input.val( accounting.formatMoney(unit_price, "P ") );
			}


			$planSelect.on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				$planSelect.removeClass('selected');

				$this.addClass('selected');

				console.log("Plan Select:" + $this.data('plan'))

				$plan.val( $this.data('plan') );

				if ( $plan.val() == "Politicks" ) {
					unit_price = politicks_base_price;
					$localBankPaymentWrap.addClass('hide');
					$trialPaymentWrap.removeClass('hide');

					planStr = "Politicks";
				}else{
					$localBankPaymentWrap.removeClass('hide');
					$trialPaymentWrap.addClass('hide');
				}
				
				if ( $plan.val() == "Plus" ) {
					unit_price = plus_price;
					planStr = "Politicks Plus";
				}
				if ( $plan.val() == "Premium" ) {
					unit_price = premium_price;
					planStr = "Politicks Premium";
				}

				$item_input.val( planStr );
				$item_summary.text( planStr );

				var numberOfMonths = parseInt($no_of_months.val());
				var total = unit_price * numberOfMonths;

				$no_of_months_summary.text( $no_of_months.val() );
				// $total_summary.text( total + '.00');
				$total_summary.text( accounting.formatMoney(total, "P ") );

				$unit_price_input.val( accounting.formatMoney(unit_price, "P ") );

				// $('#credit-card-payment').click()
				$('.payment-option').removeAttr('checked');
				$('.local-bank').removeAttr('checked');
				$creditFormWrap.addClass('hide');
				$localBankFormWrap.addClass('hide');

			});

			if ( $plan.val() !="Politicks" ) {
				$trialPaymentWrap.addClass('hide');
			}

			var $birthday = $('#birthday');
			
			if ( $birthday.length == 0 ) return;

			$birthday.datepicker({
				autoclose: true,
			    // todayHighlight: true
			});


			console.log( $plan.val() )



			if ( $customerRegisterForm.length != 0 ){

				$customerRegisterForm.on('submit', function(e) {
					e.preventDefault();
					var $this = $(this);

					is_query = 1;
					$registerBtn.prop('disabled', 1);

					console.log( $creditCardPayment.is(":checked") );

					$registerModal.modal('show');
					$registerModal.find('.modal-title').html('Submitting');
					$registerModal.find('.modal-body').html('<p>Form processing...</p>');
					// return;
					

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
						
						is_query = 0;
						$registerBtn.prop('disabled', 0);
						
						console.log(data)

						if ( typeof data !="undefined" && data.is_valid ) {
							
							if ( $creditCardPayment.is(":checked") && data.responseCode == "APPROVED" ) {

								$registerModal.modal('show');
								$registerModal.find('.modal-title').html('Thank you!');
								$registerModal.find('.modal-body').html('<p>Payment Successful! You are now subscribed to '+ planStr +'.</p>');
								$('#token').val('');
								
								$registerModal.on('hide.bs.modal', function() {
									window.location = base_url + 'home';	
								})
								return;

							}


							if ( $creditCardPayment.is(":checked") && data.responseCode != "APPROVED" ) {
								if ( data.responseCode == 0 && data.charge_errors !=0 && data.charge_errors !="Unauthorized" ) {
									$registerModal.modal('show');
									$registerModal.find('.modal-title').html('Error Occurs!');
									$registerModal.find('.modal-body').html( data.charge_errors );
								}else{
									console.log("token request")
									tokenRequest();
								}

								if ( data.charge_errors == "Unauthorized" ) {
									console.log("token request")
									tokenRequest();
								}
							}


							if ( $localBankPayment.is(":checked") ) {
								$registerModal.modal('show');
								$registerModal.find('.modal-title').html('Thank you!');
								$registerModal.find('.modal-body').html('<p>You are now successfully registered to ' + planStr + '.</p>' + '<p>To continue, please check your email for instructions.</p>');

								$registerModal.on('hide.bs.modal', function() {
									window.location = base_url + 'home';	
								})
							}

							if ( $TrialOption.is(":checked") ) {
								$registerModal.modal('show');
								$registerModal.find('.modal-title').html('Thank you!');
								$registerModal.find('.modal-body').html('<p>You are now successfully registered to Politicks 7-day free trial.</p>');

								$registerModal.on('hide.bs.modal', function() {
									window.location = base_url + 'home';	
								})
							}
						
							

						}

						if ( typeof data !="undefined" && !data.is_valid ) {

							$registerModal.modal('show');
							$registerModal.find('.modal-title').html('Error occurs');
							$registerModal.find('.modal-body').html('<p>Please check all the required fields.</p>');
							

							$this.find('.form-group').removeClass('has-error');
		                    $this.find('.error-help-block').text('');
		                    $.each(data.errors, function(index, val) {
		                        if (val !="") {
		                            $("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
		                        }
		                    });
						}

					});

				});
				

			};



			var $creditCardOrderDetails = $('#credit-card-order-details');
			var $orderDetails = $('#order-details-wrap');

			var $creditCardSummaryDetails = $('#credit-card-summary-details');
			var $summaryDetails = $('#summary-details');
			

			$('.payment-option').on('change', function(e) {
				var $this = $(this);

				if ( $this.val() == "Credit Card" ) {
					$creditFormWrap.removeClass('hide');
					$localBankFormWrap.addClass('hide');

					$('#order-details-wrap').appendTo('#credit-card-order-details');
					$('#summary-details').appendTo('#credit-card-summary-details');

				}
				if ( $this.val() == "Local Bank" ) {
					$localBankFormWrap.removeClass('hide');
					$creditFormWrap.addClass('hide');
					
					$('#order-details-wrap').appendTo('#local-bank-order-details');
					$('#summary-details').appendTo('#local-bank-summary-details');
				}
				if ( $this.val() == "Trial" ) {
					$localBankFormWrap.addClass('hide');
					$creditFormWrap.addClass('hide');	
				}

			});	


			


		},

		autoNumeric: function() {
		
			// accountingjs


		},

		smooth: function() {
			
			if ( $('.parallax-bg-wrap').length == 0 ) return;

			var $window = $(window);		//Window object
	
			var scrollTime = 0.5;			//Scroll time
			var scrollDistance = 200;		//Distance. Use smaller value for shorter scroll and greater value for longer scroll
			
			$window.on("mousewheel DOMMouseScroll", function(event){
				
				event.preventDefault();	
												
				var delta = event.originalEvent.wheelDelta/120 || -event.originalEvent.detail/3;
				var scrollTop = $window.scrollTop();
				var finalScroll = scrollTop - parseInt(delta*scrollDistance);
					
				TweenMax.to($window, scrollTime, {
					scrollTo : { y: finalScroll, autoKill:true },
						ease: Power1.easeOut,	//For more easing functions see http://api.greensock.com/js/com/greensock/easing/package-detail.html
						autoKill: true,
						overwrite: 5							
					});
							
			});


			addEventListener(document, "touchstart", function(e) {
			    console.log(e.defaultPrevented);  // will be false
			    e.preventDefault();   // does nothing since the listener is passive
			    console.log(e.defaultPrevented);  // still false
			  }, Modernizr.passiveeventlisteners ? {passive: true} : false);

		},

		parallaxBG: function() {
			
			if ( $('.parallax-bg-wrap').length == 0 ) return;

			var controller = new ScrollMagic.Controller();

			// var scene = new ScrollMagic.Scene({
			// 		triggerElement: '#issues-section',
			//         duration: '90%',
			//         triggerHook: 0.5
			//         // offset: 50   
			//     })
			// 	.setClassToggle('#our-data-landing .landing-content', 'fade-out')
			// 	// .addIndicators()
			//     .addTo(controller); // assign the scene to the controller




			$.each($('.parallax-bg-wrap'), function(index, val) {
				var $this = $(this);

				var y_offset = ( $this.data('y-offset') ) ? $this.data('y-offset') : '-50%';
				var parallaxTL = new TimelineMax();
				parallaxTL
					.from('#'+$this.attr('id') + ' .content-wrapper', 1, { autoAlpha: 0, ease: Power0.easeNone,  }, 0.2 )
					.from('#'+$this.attr('id') + ' .bg-image', 4, { y: y_offset, ease: Power0.easeNone,  }, 0)

				var slideParallaxScene = new ScrollMagic.Scene({
					triggerElement: '#'+$this.attr('id') + ' .content-wrapper',
					triggerHook: 1,
					duration: '100%',
				})
				// .addIndicators()
				.setTween(parallaxTL)
				.addTo(controller);

			});

		},

		feedBackModal: function() {
		

			var $feedBackModal = $('#feedback-modal')
			var $feedBackModalTrigger = $('.feedback-modal-trigger');
			var $feedbackSubmitBtn = $('#feedback-submit-btn');

			$feedBackModalTrigger.on('click', function(e) {
				e.preventDefault();

				// $('#feedback-message-wrap').addClass('hide').find('.alert-body').html('');
				
				$feedBackModal.modal('show');
			});

			$('#feedback-form').on('submit', function(e) {
				e.preventDefault();
				var $this = $(this);

				$feedbackSubmitBtn.prop('disabled', 1);
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

					$feedbackSubmitBtn.prop('disabled', 0);

					if ( typeof data !="undefined" && data.is_valid ) {
						$('#feedback-modal .form-group').removeClass('has-error');
	                    $('#feedback-modal .help-block').text('');
	                    $('#feedback-modal .form-control').val('');

	                    $feedBackModal.modal('hide');
	                    $('#feedback-message-modal').modal('show');
	                    // $('#feedback-message-wrap').removeClass('hide').find('.alert-body').html('<strong>Thank you for contacting us!</strong> <br /> We’ll get back to you as soon as possible! :)');

					}

					if ( typeof data !="undefined" && !data.is_valid ) {
						$('.form-group').removeClass('has-error');
	                    $('.error-help-block').text('');
	                    $.each(data.errors, function(index, val) {
	                        if (val !="") {
	                            $("#feedback-modal #" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
	                        }
	                    });
					}

					// console.log(data)
					// console.log("complete");
				});

			});

		},

		mainNav: function () {
			var $mainNavigation = $("#main-navigation");

			$(window).scroll(function() {
				
				var scrollTop = $(window).scrollTop();
				if ( scrollTop > 200 ) {
					$mainNavigation.addClass('active-top');
				}else{
					$mainNavigation.removeClass('active-top');
				}

			});



			$('#main-navigation .navbar-nav > li > a').on('click', function(e) {
				e.preventDefault()
				var $this = $(this);

				if ( $($this.data('section')).length == 0 ){
					window.location = $this.attr('href');
					return;
				}

				if ( $('.navbar-collapse').hasClass('in') ) {
					$('.navbar-toggle').click();
				}
				
				main.animate2ID($this.data('section'));

				setTimeout(function() {
					if(history.pushState) {
					    history.pushState(null, null, $this.data('section'));
					}
					else {
					    location.hash = $this.data('section');
					}
				}, 500)
			});

			$('.sige-logo').on('click', function(e) {
				if ( $('#about').length == 0 ) {
					window.location = base_url;
				}

				if ( $mainNavigation.hasClass('active-top') ) {
					e.preventDefault();



					main.animate2ID('#c');

					setTimeout(function() {
						if(history.pushState) {
						    history.pushState(null, null, '#');
						}
						else {
						    location.hash = '#';
						}
					}, 500)
				}
			})


		},

		planSlider: function() {
		
			if ( $("#plans-slider").length == 0 ) return;

			if ( $('#plans-slider').hasClass('init-done') ) return;

            var $plansSlider = $("#plans-slider").owlCarousel({
                items: 1,
                margin: 250,
                loop: 1,
                center: 1,
                nav: 1,
                dots: 0,
                navContainer: '#plans-slider-nav',
                // navText: '',
                // responsiveRefreshRate : 200,
                navText: ['<svg width="100%" height="100%" viewBox="0 0 11 20"><path style="fill:none;stroke-width: 1px;stroke: #333333;" d="M9.554,1.001l-8.607,8.607l8.607,8.606"/></svg>','<svg width="100%" height="100%" viewBox="0 0 11 20" version="1.1"><path style="fill:none;stroke-width: 1px;stroke: #333333;" d="M1.054,18.214l8.606,-8.606l-8.606,-8.607"/></svg>'],
                autoplay: 0,
                // animateOut: 'fadeOut',
                // animateIn: 'scaleUpFrom',
                onInitialize: function() {
                    // $('#home-slider .image.wh').removeClass('wh');
                    // $('#home-slider').addClass('no-nav');
                }
            });

		},

		animate2ID: function (id2Animate, callback) {
			var animSpeed=400; 
		    var easeType="easeInOutExpo"; 
		    var offset = $(id2Animate).offset().top;	     
	      
		    if($.browser.webkit){ 
		        $("body").stop().animate({scrollTop: offset, complete: function () {
		          callback({ done: true })
		        }}, animSpeed, easeType);
		    } else {
		        $("html").stop().animate({scrollTop: offset, complete: function () {
		           callback({ done: true })
		        }}, animSpeed, easeType);
		    }
		},

		bindEvents: function () {

			if ( $('.share-btn').length !=0 ) {
				$('.share-btn').MSSocial();	
			}
			

			$('a[data-toggle="tab"]').on('click', function (e) {
				e.preventDefault();
			});

		},
		lazyload: function () {
			
			if(!jQuery().lazyload) {
				$(".lazy").removeClass('lazy');
				return;
			}

			// console.log("lazy")
			$.each($('.lazy'), function(i, val) {
				var $this = $(this);
				$this.lazyload({ 
				    effect: "fadeIn",
				    effectspeed: 1000,
				}).removeClass("lazy");
			});

			$(document).ajaxStop(function(){
			    $.each( $('.lazy') , function(index, val) {
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
				// console.log("shown modal")
			});

		},

		bootstrapPlugins: function () {

			if ( $('[data-toggle="tooltip"]').length !=0 ) {
				$('[data-toggle="tooltip"]').tooltip();
			}

	    },	

	    verticalCenter: function() {
	    	
	    	var $el = $('.vertical-center');
		    
		    $.each($el, function(index, val) {
		    	var $this = $(this);

		    	$this.css({
			    	'position' : 'absolute',
			        'left' : '50%',
			        'top' : '50%',
			        'margin-left' : - $this.width()/2,
			        'margin-top' : - $this.height()/2,
			    });

			    if ( $this.height() > $(window).height() ) {
		    		$this.css('margin-top', 0).css('top', 100);
		    	};

				$(window).resize(function (){
				    $this.css({
				        'position' : 'absolute',
				        'left' : '50%',
				        'top' : '50%',
				        'margin-left' : - $this.width()/2,
				        'margin-top' : - $this.height()/2
				    });
				});
		    });

	    },

	    windowOnResize: function () {
			
			$(window).on('resize', function () {
				main.windowHeight();
			});

		},

	    windowHeight: function () {

			if ( $('.wh').length == 0 ) return;
			$.each( $('.wh'), function(index, val) {
				var $this = $(this);
				var offset = ($this.data('offset')) ? parseInt($this.data('offset')) : 0;
				var padding = ($this.data('padding')) ? parseInt($this.data('padding')) : 0;

				$this.height($(window).height() - offset + padding);
			});

			
		},


	};

	main.init();


	// PAYMENT


	// Called when token created successfully.
    successCallback = function(data) {

        $('#token').val(data.response.token.token);
        console.log(data.response.token.token);
        $customerRegisterForm.submit();
    };

    // Called when token creation fails.
    var errorCallback = function(data) {
        console.log(data)
        if (data.errorCode === 200) {
            tokenRequest();
        }else {
            // alert(data.errorMsg);

            $registerModal.modal('show');
			$registerModal.find('.modal-title').html('Error occurs!');
			$registerModal.find('.modal-body').html('<p>'+ data.errorMsg +'</p>');

            // bootbox.dialog({
            //     title: "Error occurs!",
            //     message: data.errorMsg,
            //     buttons: {
            //         dismiss: {
            //           label: "Dismiss",
            //           className: "btn-sm btn-default",
            //           callback: function () {
                        
            //           }
            //         }
            //     }
            // });
        }
    };

    tokenRequest = function() {
        // Setup token request arguments
        var args = {
            // sellerId: "901324533",
            // publishableKey: "2FC98B1F-7AFF-465C-B63C-96F6CEADE139", //kevincarpio

            sellerId: "202723376",
            publishableKey: "07A4E463-81E0-4353-8DE7-DC8DA3D24F6D", //politicks
            ccNo: $("#card_number").val(),
            cvv: $("#cvv").val(),
            expMonth: $("#expiration_month").val(),
            expYear: $("#expiration_year").val()
        };

        // Make the token request
        TCO.requestToken(successCallback, errorCallback, args);
    };

    if ( typeof TCO !="undefined" && $customerRegisterForm.length !=0 ) {
    	TCO.loadPubKey('production', function() {
	        console.log("production loadPubKey")
	    });	
    }
    


	

});		