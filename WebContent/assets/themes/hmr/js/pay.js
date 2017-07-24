jQuery(document).ready(function($) {
	
	var base_url = $('#base_url').val();
	var $MSPaymentForm = $('#payment-form');
	var $contactShippingForm = $('#checkout-contact-shipping-form');
	var $newAccountForm = $('#checkout-register-new-account');
	var $checkoutAccount = $('#checkout-account-preview');
	var is_query = 0;

	var MSPay = {
		init: function () {
			this.bindEvents()
			this.validateContactShipping()
			this.paymentForm()
			this.applyDiscount()
		},

		bindEvents: function () {
			
			MSPay.toggleCreateAccount()

		},

		toggleCreateAccount: function () {
			
			$('#toggle-register-account').on('change', function () {
				if(this.checked) {
					$newAccountForm.removeClass('hide');
					$checkoutAccount.removeClass('hide');
			    }else{
			    	$checkoutAccount.addClass('hide');
			    	$newAccountForm.addClass('hide');
			    };
			});

		},

		validateContactShipping: function () {
			
			var $billingPanel = $('#billing-panel');
			var $contactShippingPrev = $('#contact-shipping-preview');
			var $btnSubmitContactShip = $('#submit-contact-shipping-btn');
			var $btnUseShipping = $('#use-shipping-address-checkbox');
			var $billingAddressForm = $('#billing-address-form');

			$contactShippingForm.find('.form-control').on('blur', function () {
				$(this).parents('.form-group').removeClass('has-error');
			});

			$contactShippingForm.on('submit', function (e) {
				e.preventDefault();
				var $this = $(this);

				$btnSubmitContactShip.val('Submitting...');

				if ( is_query ) return;

				is_query = 1;
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
					$btnSubmitContactShip.val('Place Order').blur();

					console.log(data)
					if ( data.is_valid ) {
						$('.form-group').removeClass('has-error').find('.help-block').text('');
						
						bootbox.dialog({
							title: "Congratulations!", 
							message: "<p>Your order has been submitted.</p><p>Please check your email for instructions to complete your order. Thank you!</p>",
							buttons: {
								dismiss: {
							      label: "Dismiss",
							      className: "btn-sm btn-default",
							      callback: function () {
							      	// location.reload();
							      	window.location = base_url + 'account';
							      }
							    }
							}
						});

						// $billingPanel.removeClass('hide');
						// $contactShippingForm.addClass('hide');

						// $contactShippingPrev.removeClass('hide');

						// if ( typeof data.post.username !="undefined" && data.post.username !=null ) {
						// 	$newAccountForm.remove();
						// 	$('#toggle-register-wrap').remove();
						// };

						// $.each(data.post, function(index, val) {
						// 	// console.log(index)
						// 	$('#'+index + '-preview').text(val); //show preview
						// });

						$btnUseShipping.prop('checked', 'checked');
						
						MSPay.useShippingAddress(data);

						// $billingAddressForm.find('.form-control').prop('readonly', true);


					}else{

						// $billingPanel.addClass('hide');
						// $btnUseShipping.prop('checked', false);
						// $billingAddressForm.removeClass('hide').find('.form-control').prop('readonly', false)


						$('.form-group').removeClass('has-error').find('.help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val);
			            	}
			            });
					}
					console.log("complete");

				});
			});


			$('#edit-contact-shipping').on('click', function (e) {
				e.preventDefault();

				$billingPanel.addClass('hide');
				$contactShippingForm.removeClass('hide');
				$contactShippingPrev.addClass('hide');
			});

			$btnUseShipping.prop('checked', 'checked');
			$billingAddressForm.find('.form-control').prop('readonly', true);

			$btnUseShipping.on('click', function () {
				if ( this.checked ) {
					$billingAddressForm.addClass('hide');
					$billingAddressForm.find('.form-control').prop('readonly', true);
				}else{
					$billingAddressForm.removeClass('hide');
					$billingAddressForm.find('.form-control').prop('readonly', false)
				}
			});

		},

		useShippingAddress: function (data) {

			var $billingAddressForm = $('#billing-address-form');

			if ( typeof data.post !="undefined" ) {
				$.each(data.post, function(index, val) {
					$billingAddressForm.find( '.'+ index ).val( val );
				});	
			};
			

	
		},

		applyDiscount: function () {
			
			var amount = $('#amount').val();
			var $grandTotalWrap = $('#grand-total-wrap');
			var $discountForm = $('#discount-form');
			var $discountPercentTr = $('#discount-percent-tr');
			var $totalSavedTr = $('#total-saved-tr');
			var $totalSaved = $('#total-saved');
			var $discountPercent = $('#discount-percent');
			var $discountFetch = $('#discount-fetching');

			var originalAmount = parseFloat(amount);
			amount = parseFloat(amount);

			console.log(amount)

			$discountForm.on('submit', function (e) {
				e.preventDefault();
				var $this = $(this);

				$discountFetch.removeClass('hide');
				$('#discount').prop('readonly', true);
				$('#discount-btn').prop('disabled', true);

				$discountPercentTr.addClass('hide');
				$totalSavedTr.addClass('hide');
				$discountPercent.text('0%');
				$totalSaved.text("0.00");
				$('#discount_val').val( '' );

				$('#amount').val(originalAmount);
				$grandTotalWrap.text(originalAmount);

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
					
					$discountFetch.addClass('hide');
					$('#discount').prop('readonly', false);
					$('#discount-btn').prop('disabled', false);

					console.log(data)
					if ( data.is_valid ) {
						$discountPercentTr.removeClass('hide');
						$totalSavedTr.removeClass('hide');
						$discountPercent.text(data.coupon.percent_off + '%');

						$('#discount').parents('.form-group').removeClass('has-error').find('.help-block').text('');
						console.log(data.coupon.percent_off)
						
						var total_save = ( amount * ( data.coupon.percent_off / 100 ) ).toFixed(2);

						var grandTotal = (amount - total_save).toFixed(2);
						
						$totalSaved.text(total_save);
						$('#amount').val(grandTotal);
						$('#discount_val').val( $('#discount').val() );

						$grandTotalWrap.text(grandTotal);
						console.log(grandTotal);

					}else{
						$discountPercentTr.addClass('hide');
						$totalSavedTr.addClass('hide');
						$discountPercent.text('0%');
						$totalSaved.text("0.00");
						$('#discount_val').val( '' );

						$('#amount').val(originalAmount);
						$grandTotalWrap.text(originalAmount);

						$('#discount').parents('.form-group').addClass('has-error').find('.help-block').text(data.errors.discount);
					}
					console.log("complete");
				});

			});

		},



		paymentForm: function () {
			
			// if ( typeof Stripe == "undefined" ) {
			// 	location.reload();
			// };

			// Stripe.setPublishableKey('pk_test_6LqOquhuOcdoblohWc9VKK4p');

		    $MSPaymentForm.on('submit', function (e) {
		    	var $form = $(this);

		    	console.log('submit');

		    	var cardNumber = $('#card_number').val();
		    	var expiration = $('#expiration').val().split('/');
		    	var cvc = $('#cvc').val();

		    	if ( Stripe.card.validateCardNumber( cardNumber ) == false ) {
		    		$('#card_number').parents('.form-group').addClass('has-warning').find('.help-block').text("Invalid card number");
		    	}else{
		    		$('#card_number').parents('.form-group').removeClass('has-warning').find('.help-block').text("");
		    	}


		    	if ( Stripe.card.validateExpiry(expiration[0], expiration[1]) == false ) {
		    		$('#expiration').parents('.form-group').addClass('has-warning').find('.help-block').text("Invalid expiration number");
		    	}else{
		    		$('#expiration').parents('.form-group').removeClass('has-warning').find('.help-block').text("");
		    	}

		    	if ( Stripe.card.validateCVC(cvc) == false ) {
		    		$('#cvc').parents('.form-group').addClass('has-warning').find('.help-block').text("Invalid cvc number");
		    	}else{
		    		$('#cvc').parents('.form-group').removeClass('has-warning').find('.help-block').text("");
		    	}
		    	

		    	if ( Stripe.card.validateCardNumber( cardNumber ) && Stripe.card.validateExpiry(expiration[0], expiration[1]) && Stripe.card.validateCVC(cvc) ) {
		    		$form.find('button').prop('disabled', true);

		    		Stripe.card.createToken({
			        	number: cardNumber,
						cvc: cvc,
						exp_month: expiration[0],
						exp_year: expiration[1]
			        }, MSPay.stripeResponseHandler );

		    		
		    	};
		       
		        return false;
		        e.preventDefault()
		    })

		},

		stripeResponseHandler: function (status, response) {
			
			console.log( status )
			console.log( response )

			if (response.error) {
				// Show the errors on the form
				$MSPaymentForm.find('.payment-errors').text(response.error.message);
				$MSPaymentForm.find('button').prop('disabled', false);
			} else {
				
				$MSPaymentForm.find('.payment-errors').text('');

				// token contains id, last4, and card type
				var token = response.id;
				// Insert the token into the form so it gets submitted to the server
				$MSPaymentForm.append($('<input type="hidden" name="stripeToken" />').val(token));
				// and re-submit
				// $MSPaymentForm.get(0).submit();
				// return false;

				$.ajax({
					url: $MSPaymentForm.attr('action'),
					type: 'POST',
					dataType: 'json',
					data: $MSPaymentForm.serializeArray(),
				})
				.fail(function(e) {
					$('body').append(e.responseText)
					console.log(e.responseText)
					console.log("error");
				})
				.always(function(data) {
					console.log(data)

					if ( data.is_valid ) {
						// redirect to orders
						
						// window.location = base_url + 'account/'
					}else{

						$MSPaymentForm.find('button').prop('disabled', false);
						$('.form-group').removeClass('has-error').find('.help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$MSPaymentForm.find("." + index).parents('.form-group').addClass('has-error').find('.help-block').text(val);
			            	}
			            });

					}
					console.log("complete");
				});
						

			}
		},


	};

	MSPay.init();

});			