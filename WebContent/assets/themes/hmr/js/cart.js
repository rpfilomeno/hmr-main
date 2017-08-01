jQuery(document).ready(function($) {
	
	var base_url = $('#base_url').val();
	var variantValues = [];
	var $addToCartForm = $('#add-to-cart-form');
	var addToCartText = 'Add to cart';
	var currentURL = new Url;
	
	var MSCart = {
		init: function () {
			this.bindEvents();
		},

		bindEvents: function () {
			MSCart.addToCart()
			MSCart.variantSelect()
			MSCart.productGallery()
		},

		addToCart: function () {
			

			var $bagCount = $('#bag-count');
			var cartCount = ( $.trim($bagCount.text()).length !=0 ) ? parseInt($bagCount.text()) : 0;

			$addToCartForm.on('submit', function (e) {
				e.preventDefault();
				var $this = $(this);
				console.log("submit")

				$this.find('.btn-add-to-cart').val('Adding...').addClass('disabled').prop('disabled', true);

				MSCart.addToCartSubmit($this.serializeArray(), function (data) {
					console.log(data)
					if ( data.is_valid ) {

						$('.cart--total').text( 'P' + data.cart.total );
						$('.cart--total--items').text( data.cart.total_items );

						$('.go-to-shopping-cart-order-checkout').removeClass('hide');
						
						$('.form-group').removeClass('has-warning').find('.help-block').text('');
						$this.find('.btn-add-to-cart').val('Added!');

						cartCount += parseInt(data.post_data.qty);

						console.log(cartCount)

						$bagCount.text(cartCount)

						setTimeout(function () {
							$this.find('.btn-add-to-cart').val(addToCartText).removeClass('disabled').prop('disabled', false);
						}, 2000);
					}else{

						$this.find('.btn-add-to-cart').val(addToCartText).removeClass('disabled').prop('disabled', false);
						$('.form-group').removeClass('has-warning');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			                	$("#" + index).parents('.form-group').addClass('has-warning').find('.help-block').text(val);
			            	}
			            });

					}
				});
			});

		},

		addToCartSubmit: function (data, callback) {
			
			$.ajax({
				url: base_url + 'cart/add',
				type: 'POST',
				dataType: 'json',
				data: data,
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(callback);

		},

		variantSelect: function () {
			
			var $variantSelect = $('.variant-select');
			var variantCombinations = [];

			$.each($('.variant-combination-hidden'), function(i, val) {
				var $this = $(this);
				variantCombinations.push({
					combination: $this.val().split(','),
					price: $this.data('price'),
					sale_price: $this.data('sale-price'),
					stock: $this.data('stock'),
					sku: $this.data('sku'),
					on_sale: $this.data('on-sale'),
					id: $this.data('id'),
				});
			});

			// console.log(variantCombinations);


			$variantSelect.on('change', function (e) {
				e.preventDefault();
				var $this = $(this)
				, optionVal = $this.find('option:selected').val();
				console.log( optionVal )
				
				$('.price-form-text').addClass('hide');

				$this.parents('.form-group').removeClass('has-warning');
				MSCart.checkCombination(variantCombinations);
			});

		},

		checkCombination: function (variantCombinations) {
			
			var $variantSelect = $('.variant-select');

			variantValues.length = 0;

			$.each($variantSelect, function(i, val) {
				variantValues.push($(this).find('option:selected').val());
			});

			console.log(variantValues)		
			console.log(variantCombinations)		

			$.each(variantCombinations, function(i, val) {
				var combination = val.combination;

				if ( $(combination).not(variantValues).length === 0 && $(variantValues).not(combination).length === 0 ) {
					console.log(val)
					if ( val.on_sale == 1 ) {
						var currency = $('#single-product-wrapper').data('currency');
						$('.pricing-wrap').html( '<span class="currency-wrap">'+currency+'</span><span class="price-wrap">' + val.sale_price + ' ' + '<span class="strikeout text-muted">'+ currency + val.price +'</span></span>');
						$('#cur-price').val(val.sale_price);
					}else{
						$('.price-wrap').text(val.price);
						$('#cur-price').val(val.price);
					}
					

					// alert('martch!');
				};
			});
		},

		productGallery: function () {

			var $productPreview = $('#product--image--preview');
			var $productGallery = $('#product--gallery--wrapper');

			var $fileList = $productGallery.msis({
				ajaxOptions:{
					data: {
						ajax: 1,
						tpl: 'tpl',
						size: 'normal',
						attach_id: $productGallery.data('product-id'),
					}
				},
				errorMessage: '',
				errorClass: 'col-md-8',
				loader_image: base_url + 'img/loader.gif',
				trigger_type: "button",
				load_more_class: "btn btn-sm btn-primary",
				load_more_text: 'Load more image',

				created: function (el) {
					// console.log(el.attr('id'))

					// el.trigger('get_contents');
				},
				responseError: function (e, el, response) {
					console.log(response.responseText)
				},
				done: function (e, el, response) {

					// console.log(response);
					if ( typeof response.is_valid != "undefined" && response.is_valid ) {


						$.each(response.results_obj, function(i, val) {
							var imgClass = (i == 0) ? 'active' : '';
							if ( i == 0 ) {
								$('#path').val(val.path);
								$('#file_name').val(val.file_name);
								
								$productPreview.html('<img class="img-responsive margin-auto animated fadeIn" alt_text="'+ val.alt_text +'" src="'+ val.web_image +'" />');
							};

							setTimeout(function () {
								$productGallery.append('<li class="col-lg-2 col-md-3 col-xs-4 '+ imgClass +'"><a class="product-thumb" data-alt-text="'+ val.alt_text +'" data-path="'+ val.web_image +'" href="#"><img class="img-responsive margin-auto animated fadeIn" src="'+ val.thumb_path +'" /></a></li>');
							}, 500 * i);

						});

					}else{
						$productPreview.html('<img class="img-responsive margin-auto animated fadeIn" src="'+ base_url + 'img/no-image-available.jpg' +'" />');
					}
					
				}
			});

			$('.product-thumb').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);
				$productGallery.find('li').removeClass('active');
				$this.parents('li').addClass('active');
				$productPreview.html('<img class="img-responsive margin-auto animated fadeIn" alt_text="'+ $this.data('alt-text') +'" src="'+ $this.data('path') +'" />');
			});

		},




	};

	MSCart.init();


});	