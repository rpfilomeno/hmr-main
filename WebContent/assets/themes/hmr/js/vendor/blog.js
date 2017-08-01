jQuery(document).ready(function($) {
	
	var base_url = $('#base_url').val()
	,	max_file_count = 100
	,	currentURL = new Url
	,	is_query = 0
	,	variants1 = []
	,	variants2 = []
	,	variants3 = []
	,	$addPostForm = $('#add-post-form')
	,	somethingChanged = 0;

	var map, autocomplete;

	var MSBlog = {
		init: function () {
			this.bindEvents();
			
		},

		bindEvents: function () {
			
			MSBlog.search();
			MSBlog.filter();
			MSBlog.addNew();
			MSBlog.priceInput();
			MSBlog.markAsPaid();

			if ( currentURL.query.id != null && currentURL.query.page_id !=null) {
				MSBlog.addImages({
					attach_id: currentURL.query.id
				});

				// MSBlog.post_product_msis();
				MSBlog.post_product_action();
			};

			console.log( currentURL.query )
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
				      	
				      	MSBlog.submitPayment($this.data('id'), $this.data('event-id'), $this.data('customer-id'), $this.data('status'));

				      }
				    },
				  }
				});

				
					

			});

		},

		submitPayment: function(id, event_id, customer_id, status) {
		
			$.ajax({
				url: base_url + 'cms/blog/mark_as_paid',
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

		priceInput: function () {
			
			if ( $.fn.autoNumeric ) {
            	$('.price-input').autoNumeric();
        	};

		},

		search: function () {
			
			$('#product-search-form').on('submit', function (e) {
				e.preventDefault();
			 	var serializeArray = $(this).serializeArray()
				currentURL.query.q = serializeArray[0].value;
				if ( serializeArray[0].value == '' ) {
					delete currentURL.query.q;
				};
				window.location = currentURL.toString();
			});

		},

		filter: function () {
			
			$('#category_filter').on('change', function () {
				var optionSelected = $(this).find("option:selected").val();
				if ( optionSelected != '' ) {
					currentURL.query.category = optionSelected;
				}else{
					delete currentURL.query.category;
				}
				// delete currentURL.query.start;
				window.location = currentURL.toString();
			});

			$('#date_filter').on('change', function () {
				var optionSelected = $(this).find("option:selected").val();
				console.log(optionSelected)
				if ( optionSelected != '' ) {
					currentURL.query.date = optionSelected;
				}else{
					delete currentURL.query.date;
				}
				delete currentURL.query.start;
				window.location = currentURL.toString();
			});

			$('.post-status-tab > li > a').on('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				currentURL.query.status = $this.data('status');

				if ( $this.data('status') == "all" ) {
					delete currentURL.query.status;
				};
				window.location = currentURL.toString();
			});

			console.log(currentURL.query)

		},

		addNew: function () {			

			if ( $('.main-post-wrap #title').length !=0 ) {
				autosize( $('.main-post-wrap #title') );
			}

			$('.add-new-post-btn').on('click', function (e) {
				e.preventDefault();
				var $this = $(this);
				console.log("click")

				if ( $this.data('parent') !=0 ) {
					
					if ( is_query ) return;
					is_query = 1;

					$.ajax({
						url: base_url + 'cms/blog/anp',
						type: 'POST',
						dataType: 'json',
						data: {
							title: 'Untitled',
							parent: $this.data('parent'),
							type: 'blog_post',
							status: 'unsaved',
						},
					})
					.fail(function(e) {
						console.log(e.responseText)
						console.log("error");
					})
					.always(function(data) {
						is_query = 0;
						console.log(data)
						console.log("complete");
						
						if ( data.is_valid ) {
							window.location = base_url + 'cms/blog/post?page_id=' + $this.data('parent') + '&id=' + data.id + '&add_new=1';
						}
					});
							
				};
			});

			

			$addPostForm.on('submit', function (e) {
				e.preventDefault();
				var $this = $(this);

				if ( is_query ) return;
				is_query = 1;

				$('.tab-has-error').removeClass('tab-has-error');

				$.ajax({
					url: $this.attr('action'),
					type: 'POST',
					dataType: 'json',
					data: $this.serializeArray(),
				})
				.fail(function(e) {
					console.log(e.responseText)
					// $('body').append(e.responseText)
					console.log("error");
				})
				.always(function(data) {
					is_query = 0;
					console.log(data)
					if ( data.is_valid ) {

						MSPB2.submitContent(function (pbdata) {
							console.log(pbdata)
							if ( pbdata.is_valid ) {
								// location.reload();
								somethingChanged = 0;
							};
						});

						webArtJs.messenger({
							message: "Post has been saved!",
							closeAfter: 3000,
							position: 'top-left',
							closeBtn: 1,
						});

						setTimeout(function () {
							window.location = base_url + 'cms/blog/post?page_id=' + currentURL.query.page_id + '&id=' + data.id;
						}, 1000);

					}else{

						webArtJs.messenger({
							header: "",
							message: 'Please check the required field/s',
							closeAfter: 3000,
							position: "small top-left",
							closeBtn: true,
						});

						$('.form-group').removeClass('has-error');
						$('.error-help-block').text('');
			            $.each(data.errors, function(index, val) {
			            	if (val !="") {
			            		var $navLi = $('#'+ $("#" + index).parents('.form-group').parents('.tab-pane').attr('id') + '-li' );
			            		$navLi.addClass('tab-has-error');;
			                	$("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
			            	}
			            });

					}
					console.log("complete");
				});
					

			});

			$('.post-action').on('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				if ( $this.data('type') == "published" || $this.data('duplicate') ) {
					// $('#post-action-status').val($this.data('type'));	
					$addPostForm.submit();
				}

				if ( $this.data('type') == "cancel" ) {
					
					bootbox.dialog({
					  title: "Cancel Confirmation",
					  message: "Are you sure you wish to leave this page without saving?",
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
					    	window.location = base_url + 'cms/blog?page_id=' + currentURL.query.page_id;
					    	// location.reload();
					      }
					    },
					  }
					});

				};

				console.log( $this.data('type') )

				if ( $this.data('type') == "delete" ) {

					bootbox.dialog({
					  title: "Delete Confirmation",
					  message: "Are you sure you wish to delete this post?",
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
					    	MSBlog.setDelete(currentURL.query.id, function (data) {
					    		if ( data.is_valid ) {
					    			webArtJs.messenger({
										message: "Post has been deleted!",
										closeAfter: 3000,
										position: 'top-left',
										closeBtn: 1,
									});
									window.location = base_url + 'cms/blog?page_id=' + currentURL.query.page_id;
					    		}else{
					    			webArtJs.messenger({
										message: "There's a problem deleting the post. Please try again later.",
										closeAfter: 3000,
										position: 'top-left',
										closeBtn: 1,
									});
					    		}
					    	});
					    	
					      }
					    },
					  }
					});

				}
				

				
			});



			$('#post_url').on('change', function () {
				$('#use_post_url').val(1);
			});

			MSBlog.tags();
			MSBlog.blogCategory();
			MSBlog.dateTimePicker();
			MSBlog.postImage();
			MSBlog.confirmLeave();
			MSBlog.eventDateTimePicker()
			

			$('.blog-post-form-tab a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
				var $this = $(this);
				console.log($this.parents('li').attr('id'))
				if ( $this.parents('li').attr('id') == "locations-tab-li" && !$this.hasClass('map-loaded') ) {
					MSBlog.placeAutocomplete();
					MSBlog.addLocation();		
					$this.addClass('map-loaded');
				}
			})

			var $membershipDetailsWrap = $('#membership-details-wrap');
			$('#add_memberhip_form').on('change', function(e) {
				var $this = $(this);

				if ( $this.prop('checked') ) {
					$membershipDetailsWrap.removeClass('hide');
				}else{
					$membershipDetailsWrap.addClass('hide');
				}
			})
			

		},

		eventDateTimePicker: function() {
		
			if ( $('#event-start-date-picker').length == 0) return;

			// EVENT START DATE
			var $eventDateFrom = $('#event_date_from');
			var $eventStartMin = $('#event_start_post_minutes');
		    var $eventStartSec = $('#event_start_post_seconds');
		    var $eventStartMer = $('#event_start_meridian');
		    var $eventStartWrap = $('#event-start-datetime-wrap');

		     // EVENT END DATE
			var $eventDateTo = $('#event_date_to');
			var $eventEndMin = $('#event_end_post_minutes');
		    var $eventEndSec = $('#event_end_post_seconds');
		    var $eventEndMer = $('#event_end_meridian');
		    var $eventEndWrap = $('#event-end-datetime-wrap');

		    var $eventStartDate = $('#event-start-date-picker').datepicker({
				todayHighlight: true,
				format: 'yyyy-mm-dd',
			});

			var $eventEndDate = $('#event-end-date-picker').datepicker({
				todayHighlight: true,
				format: 'yyyy-mm-dd',
			});

			var formattedDateFrom = $eventStartDate.datepicker('getFormattedDate');
			var formattedDateTo = $eventEndDate.datepicker('getFormattedDate');


			$eventStartDate.on('changeDate', function(e) {
				var $this = $(this);
				formattedDateFrom = $this.datepicker('getFormattedDate');
				var date_arr = formattedDateFrom.split('-');

		        // $eventDateFrom.val( formattedDateFrom )

		        // $('#event-start-date-str').text( formatEventDate(formattedDateFrom) );

		        var momentA = moment(formattedDateFrom + ' ' + $eventStartMin.val() + ':' + $eventStartSec.val() + $eventStartMer.val(), "YYYY-MM-DD HH:mm a").unix();
			    var momentB = moment(formattedDateTo + ' ' + $eventEndMin.val() + ':' + $eventEndSec.val() + $eventEndMer.val(), "YYYY-MM-DD HH:mm a").unix();

			    if ( momentA <= momentB ) {
			    	$eventDateFrom.val( formattedDateFrom )
			    	$('#event-start-date-str').text( formatEventDate(formattedDateFrom) );
			    	console.log("Good range");
			    }else{
			    	console.log("Bad range");

			    	$eventDateFrom.val( formattedDateFrom )
			    	$('#event-start-date-str').text( formatEventDate(formattedDateFrom) );
			    	
			    	// $eventStartWrap.addClass('hide');
			    	$eventEndDate.datepicker('update', formattedDateFrom);
			    	$eventDateTo.val( formattedDateFrom )
			    	$('#event-end-date-str').text( formatEventDate(formattedDateFrom) );
			    }


		    });


		    $('.event_start_time').on('change', function(argument) {
		    	var $this = $(this);
		    	$('#event-start-time-str').text( $eventStartMin.val() + ':' + $eventStartSec.val() + $eventStartMer.val() );
		    });		    

		    $('.event-start-toggle').on('click', function(e) {
		    	e.preventDefault()
		    	$eventStartWrap.toggleClass('hide');
		    });


		    // EVENT END DATE
			

			$eventEndDate.on('changeDate', function(e) {
				var $this = $(this);
				formattedDateTo = $this.datepicker('getFormattedDate');
				var date_arr = formattedDateTo.split('-');

		        console.log("formattedDateFrom:" + formattedDateFrom)

		        var momentA = moment(formattedDateFrom + ' ' + $eventStartMin.val() + ':' + $eventStartSec.val() + $eventStartMer.val(), "YYYY-MM-DD HH:mm a").unix();
			    var momentB = moment(formattedDateTo + ' ' + $eventEndMin.val() + ':' + $eventEndSec.val() + $eventEndMer.val(), "YYYY-MM-DD HH:mm a").unix();

			    if ( momentA <= momentB ) {
			    	$eventDateTo.val( formattedDateTo )
			    	$('#event-end-date-str').text( formatEventDate(formattedDateTo) );
			    	console.log("Good range");
			    }else{
			    	console.log("Bad range");

			    	$eventEndWrap.addClass('hide');
			    	$eventEndDate.datepicker('update', formattedDateFrom);
			    	$eventDateTo.val( formattedDateFrom )
			    	$('#event-end-date-str').text( formatEventDate(formattedDateFrom) );


			    	bootbox.dialog({
					  title: "Datetime Range Error",
					  message: "Please check the start and end datetime.",
					  // size: "small",
					  buttons: {
					    cancel: {
					      label: "Dismiss",
					      className: "btn-default btn-sm",
					      callback: function() {
					        
					      }
					    },
					  }
					});
			    }
		        


		    });


		    $('.event_end_time').on('change', function(argument) {
		    	var $this = $(this);
		    	$('#event-end-time-str').text( $eventEndMin.val() + ':' + $eventEndSec.val() + $eventEndMer.val() );
		    });		    

		    $('.event-end-toggle').on('click', function(e) {
		    	e.preventDefault()
		    	$eventEndWrap.toggleClass('hide');
		    });


		 //    function dateCompare(dateTimeA, dateTimeB) {
			//     var momentA = moment(dateTimeA,"DD/MM/YYYY ");
			//     var momentB = moment(dateTimeB,"DD/MM/YYYY");
			//     if (momentA > momentB) return 1;
			//     else if (momentA < momentB) return -1;
			//     else return 0;
			// }











		    $(document).click(function(e){
			    if ($(e.target).closest(".event-start-toggle").length == 0 && $(e.target).closest(".ms-datetime-picker").length == 0 ) {
			        $eventStartWrap.addClass('hide');
			    }

			    if ($(e.target).closest(".event-end-toggle").length == 0 && $(e.target).closest(".ms-datetime-picker").length == 0 ) {
			        $eventEndWrap.addClass('hide');
			    }
			});


			function formatEventDate(date_value) {
				var datePicked = moment( date_value );

				var dayOfWeek = datePicked.format("ddd");
				var month = datePicked.format("MMM");
				var day_num = datePicked.format("DD");
				var year = datePicked.format("YYYY");

				return dayOfWeek + ', ' + month + ' ' + day_num + ' ' + year;				
			}

		},

		addLocation: function() {
		
			if ( typeof autocomplete !="undefined" && autocomplete !=null ) {

				console.log("addLocation")

				var input = document.getElementById('pac-input');

				google.maps.event.trigger(autocomplete, 'place_changed');

				setTimeout(function() {
					google.maps.event.trigger(input, 'focus')
				    google.maps.event.trigger(input, 'keydown', {
				        keyCode: 40
				    });
				    google.maps.event.trigger(input, 'keydown', {
				        keyCode: 13
				    });
				}, 2000);
			}

		},

		placeAutocomplete: function() {
			
			var phLong = 12.4000,
			    phLat = 121.9267;
			    // phLat = '115',


			if ( $('#map').length ==0 ) return;

			map = new google.maps.Map(document.getElementById('map'), {
	            zoom: 6,
	            center: new google.maps.LatLng(phLong, phLat),
	            // mapTypeId: google.maps.MapTypeId.HYBRID,
	            mapTypeId: google.maps.MapTypeId.ROADMAP,
	            mapTypeControl: false,
	            mapTypeControlOptions: {
	                // style: google.maps.MapTypeControlStyle.DROPDOWN_MENU,
	                position: google.maps.ControlPosition.RIGHT_TOP,
	            },
	            panControl: false,
	            panControlOptions: {
	                position: google.maps.ControlPosition.TOP
	            },
	            zoomControl: true,
	            zoomControlOptions: {
	                // style: google.maps.ZoomControlStyle.SMALL,
	                position: google.maps.ControlPosition.RIGHT_CENTER,

	            },
	            scaleControl: false,
	            scaleControlOptions: {
	                position: google.maps.ControlPosition.RIGHT_TOP
	            },
	            streetViewControl: false,
	            streetViewControlOptions: {
	                position: google.maps.ControlPosition.RIGHT_TOP
	            },
	            draggableCursor: 'default',
	            scrollwheel: false,
	            // styles: [
	                      
	            //         ]
	        });
	        // var card = document.getElementById('pac-card');
	        var input = document.getElementById('pac-input');
	        var types = document.getElementById('type-selector');
	        var strictBounds = document.getElementById('strict-bounds-selector');

	        // map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);

	        autocomplete = new google.maps.places.Autocomplete(input);

	        autocomplete.setComponentRestrictions({'country': ['ph']});

	        // Bind the map's bounds (viewport) property to the autocomplete object,
	        // so that the autocomplete requests use the current map bounds for the
	        // bounds option in the request.
	        autocomplete.bindTo('bounds', map);

	        var infowindow = new google.maps.InfoWindow();
	        var infowindowContent = document.getElementById('infowindow-content');
	        infowindow.setContent(infowindowContent);
	        

	        var marker = new google.maps.Marker({
	          map: map,
	          anchorPoint: new google.maps.Point(0, -29)
	        });


			if ( $.trim($('#lat').val()).length !=0 && $.trim($('#long').val()).length !=0 ) {
				var long = $('#long').val();
				var lat = $('#lat').val();
				map.setCenter(new google.maps.LatLng(lat, long));

				map.setZoom(17);
				marker.setVisible(true);
			}




	        autocomplete.addListener('place_changed', function() {
	          infowindow.close();
	          marker.setVisible(false);
	          var place = autocomplete.getPlace();
	          
	          // console.log(place)

	          if ( typeof place =="undefined" ) {
	            // User entered the name of a Place that was not suggested and
	            // pressed the Enter key, or the Place Details request failed.
	            console.log("No details available");
	            // console.log("No details available for input: '" + place.name + "'");
	            return;
	          }

	          $('#lat').val( place.geometry.location.lat() );
	          $('#long').val( place.geometry.location.lng() );

	          console.log("lat:" + place.geometry.location.lat())
	          console.log("lng:" + place.geometry.location.lng())

	          // If the place has a geometry, then present it on a map.
	          if (place.geometry.viewport) {
	            map.fitBounds(place.geometry.viewport);
	          } else {
	            map.setCenter(place.geometry.location);
	            map.setZoom(17);  // Why 17? Because it looks good.
	          }
	          marker.setPosition(place.geometry.location);
	          marker.setVisible(true);

	          var address = '';
	          if (place.address_components) {
	            address = [
	              (place.address_components[0] && place.address_components[0].short_name || ''),
	              (place.address_components[1] && place.address_components[1].short_name || ''),
	              (place.address_components[2] && place.address_components[2].short_name || '')
	            ].join(' ');
	          }

	          infowindowContent.children['place-icon'].src = place.icon;
	          infowindowContent.children['place-name'].textContent = place.name;
	          infowindowContent.children['place-address'].textContent = address;
	          infowindow.open(map, marker);
	        });

	        // Sets a listener on a radio button to change the filter type on Places
	        // Autocomplete.
	        function setupClickListener(id, types) {
	          var radioButton = document.getElementById(id);
	          radioButton.addEventListener('click', function() {
	            autocomplete.setTypes(types);
	          });
	        }

	        // setupClickListener('changetype-all', []);
	        // setupClickListener('changetype-address', ['address']);
	        // setupClickListener('changetype-establishment', ['establishment']);
	        // setupClickListener('changetype-geocode', ['geocode']);

	        // document.getElementById('use-strict-bounds')
	        //     .addEventListener('click', function() {
	        //       console.log('Checkbox clicked! New state=' + this.checked);
	        //       autocomplete.setOptions({strictBounds: this.checked});
	        //     });

		},

		setDelete: function(id, callback) {
			
			return $.ajax({
				url: base_url + 'cms/blog/set_deleted',
				type: 'POST',
				dataType: 'json',
				data: {is_ajax: 1, id: id},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(callback);
				
		},

		dateTimePicker: function() {
			
			if(!jQuery().datepicker) return;

			var $dateTimeText = $('#datetime-text');

			var $blogDatePicker = $('#ms-datepicker-picker').datepicker({
				format: 'yyyy-mm-dd'
			});

			var $postDate = $('#post_date');
			var formattedDate = $blogDatePicker.datepicker('getFormattedDate');

			$postDate.val( formattedDate )

			console.log(formattedDate)

			MSBlog.datePostedText(formattedDate);

			$blogDatePicker.on('changeDate', function(e) {
				var $this = $(this);
				formattedDate = $this.datepicker('getFormattedDate');
				var date_arr = formattedDate.split('-');

		        $postDate.val( formattedDate )
		        
		        console.log( formattedDate )	

		        // console.log( moment(formattedDate).calendar() )

		        MSBlog.datePostedText(formattedDate);

		    });


		    var time = '<span class="post-minutes-wrap">'+ $('#post-minutes').val() +'</span>' + ':' + '<span class="post-seconds-wrap">'+ $('#post-seconds').val() +'</span>' + ' <span class="post-meridian-wrap">'+ $('#post-meridian').val() +'</span>';

		    $('.datetime-pick').live('click', function(e) {
		    	e.preventDefault();
		    	$('#post-datetime-wrap').toggleClass('hide');
		    });

			console.log( $('#status').val() )

		    if ( $('#status').val() == "draft" ) {
		    	$dateTimeText.html("This post will not be visible.");
		    }
		    if ( $('#status').val() == "scheduled" ) {
		    	MSBlog.datePostedText(formattedDate);
		    }

		    
		    $('#status').on('change', function() {
		    	var $this = $(this);
		    	if ( $this.find('option:selected').val() == "published" ) {
		    		MSBlog.datePostedText(formattedDate);
		    	}
		    	if ( $this.find('option:selected').val() == "draft" ) {
		    		$dateTimeText.html("This post will not be visible.");
		    	}
		    	if ( $this.find('option:selected').val() == "scheduled" ) {
		    		MSBlog.datePostedText(formattedDate);
		    	}
		    });

		    $(document).click(function(e){
			    if ($(e.target).closest(".datetime-pick").length == 0 && $(e.target).closest(".ms-datetime-picker").length == 0 ) {
			        $('#post-datetime-wrap').addClass('hide');
			    }
			});

			$('.post-minutes').on('change', function() {
				var $this = $(this);
				if ( $this.val() > 12 ) {
					$this.val(12);
				}
				if ( $this.val() == 0 ) {
					$this.val(1)
				}
			}).on('keyup, change', function() {
				MSBlog.datePostedText(formattedDate);
			})

			$('.post-seconds').on('change', function() {
				var $this = $(this);
				if ( $this.val() > 59 ) {
					$this.val(59);
				}
				if ( $this.val() <= 0 ) {
					$this.val(00)
				}
			}).on('keyup, change', function() {
				var $this = $(this);
				if ( $this.val() < 10 ) {
					$this.val( '0' + parseInt($this.val()) );
				}
				MSBlog.datePostedText(formattedDate);
			})

			$('#post-meridian').on('change', function() {
				MSBlog.datePostedText(formattedDate);	
			});

		},

		datePostedText: function(formattedDate) {
			
			var $dateTimeText = $('#datetime-text');
			var now = moment().format('YYYY-MM-DD');
		        

	        var datePicked = moment( formattedDate );
	        var diff = parseInt(datePicked.diff(moment(now), 'days'));
	        var time = '<span class="post-minutes-wrap">'+ $('#post-minutes').val() +'</span>' + ':' + '<span class="post-seconds-wrap">'+ $('#post-seconds').val() +'</span>' + ' <span class="post-meridian-wrap">'+ $('#post-meridian').val() +'</span>';
	        
	        console.log( diff )

	        console.log( datePicked.format("D") )

	        if ( diff == 0 ) {
	        	$dateTimeText.html( '<span class="post-stat-wrap">Publish</span> <a href="#" class="datetime-pick"> today at ' + time + '</a>');
	        }

	        if ( diff < 0 ) {
	        	$dateTimeText.html( '<span class="post-stat-wrap">Publish</span> <a href="#" class="datetime-pick"> '+ datePicked.format("MMMM D, YYYY") +' at ' + time + '</a>');
	        }

	        if ( diff == 1 ) {
	        	$dateTimeText.html( '<span class="post-stat-wrap">Publish</span> <a href="#" class="datetime-pick"> tomorrow at ' + time + '</a>');
	        }

	        if ( diff > 1 ) {
	        	$dateTimeText.html( '<span class="post-stat-wrap">Publish</span> in <a href="#" class="datetime-pick"> '+ diff +' days at ' + time + '</a>');	
	        }


	        if ( $('#status').val() == "publish" && diff < 0 ) {
				$dateTimeText.html( '<span class="post-stat-wrap">Published</span> on <a href="#" class="datetime-pick"> '+ moment(datePicked).format("MMM D") +' at ' + time + '</a>');	
			}
		},

		confirmLeave: function () {
			
			

			$('#'+ $addPostForm.attr('id') + ' .form-control').on('change', function () {
				somethingChanged = 1;
			});

			window.onbeforeunload = function(e){

				if ( somethingChanged ) {
					e = e || window.event;

				    var message = 'Are you sure you want to leave without saving?';

				    // For IE6-8 and Firefox prior to version 4
				    if (e){
				        e.returnValue = message;
				    }

				    // For Chrome, Safari, IE8+ and Opera 12+
				    return message;	
				};
				
			};
			  	

		},

		addImages: function (params) {
			
			var $filelist = $('#add-image-wrapper');

			var attachment_for = 'Product';
			var attach_id = ( params.attach_id ) ? params.attach_id : 0;
			var drop_element = 'product-drop-element';
			var $dropElement = $('#'+drop_element);
			var pickfilesBtn = 'product-pickfiles';
			var pickfilesContainer = 'product-pickfiles-container';

			var mime_types = [
				{title : "Image files", extensions : "jpg,jpeg,gif,png"},
			];

			var server_params = {
				"attach_id" : attach_id,
				"attachment_for" : attachment_for,
				"no_file_preview" : 1,
				"resize" : 1,
		    };

			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : pickfilesBtn, // you can pass an id...
				drop_element: drop_element,
				dragdrop: true,
				container: document.getElementById(pickfilesContainer), // ... or DOM Element itself
				url : base_url + 'cms/media/do_upload',
				flash_swf_url : base_url + 'js/vendor/Moxie.swf',
				silverlight_xap_url : base_url + 'js/vendor/Moxie.xap',
				filters : {
					max_file_size : ($dropElement.data('max-file-size')) ? $dropElement.data('max-file-size') : '100mb',
					mime_types: mime_types
				},
				// multi_selection: false,
				// chunks: true,
				multipart_params : server_params
			});

			uploader.init();

			

			// PostInit
			uploader.bind('PostInit', function () {
				
			});

			// UploadProgress
			uploader.bind('UploadProgress', function (up, file) {
				$('#'+file.id).find('.progress-bar').css('width', file.percent + '%').find('.sr-only').text(file.percent + '%');
			});

			// FilesAdded
			uploader.bind('FilesAdded', function (up, files) {
				console.log(up.files.length)
				if(up.files.length > max_file_count){
			        alert("Max File Count is " + max_file_count + '\n' + 'Upload ' + max_file_count + ' files at a time.');
					for(var i in files){
						uploader.removeFile(files[i]);
					};
			        return;
			    }


				for(var i in files){
					var file = files[i];
					$filelist.append('<li id="'+ file.id +'" class="selectable col-lg-3 col-md-4 col-xs-6 col-small-gutter"><div class="media-preview file-detail files-added">'+ '<div class="progress"><div class="progress-bar progress-bar-striped active" style="width: 20%" role="progressbar"><span class="sr-only"></span></div></div></div></li>');
				};
				uploader.start();
				uploader.refresh();
			});


			// Error
			uploader.bind('Error', function (up, err) {
				// console.log(up)
				console.log(err.response)
				if ( $('#'+ err.file.id).length !=0 ) {
					$('#'+ err.file.id).addClass('error-file').html('<div class="media-preview file-detail"><span class="file_name">'+ err.file.name + '</span> <span class="file_size">('+ plupload.formatSize(err.file.size) +')</span>' + '<div class="text-warning">'+ err.message +'</div>' +'</div>');
				}else{
					$filelist.append('<li id="'+ err.file.id +'" class="error-file col-lg-3 col-md-4 col-xs-6 col-small-gutter"><div class="media-preview file-detail"><span class="file_name">'+ err.file.name + '</span> <span class="file_size">('+ plupload.formatSize(err.file.size) +')</span>' + '<div class="text-warning">'+ err.message +'</div>' +'</div></li>');
				}
				uploader.refresh();
			});

			uploader.bind('FileUploaded', function (up, file, response) {
				var jsonResponse = $.parseJSON(response.response);

				console.log(jsonResponse)
				if ( jsonResponse.is_valid ) {
					$('.msis-error-message-wrapper').hide();
					var image_pathNormal = base_url + jsonResponse.upload_data.upload_full_path + '/'+ jsonResponse.upload_data.raw_name + '-510-825' + jsonResponse.upload_data.file_ext;
					var image_pathFull = base_url + jsonResponse.upload_data.upload_full_path + '/'+ jsonResponse.upload_data.raw_name + jsonResponse.upload_data.file_ext;
				
					$('#'+file.id).replaceWith('<li id="post-product-'+jsonResponse.upload_data.media_id+'"><div class="post-product-controls"> <div class="btn-toolbar" > <div class="btn-group"> <button type="button" data-media-id="'+ jsonResponse.upload_data.media_id +'" data-action="edit" class="btn btn-xs btn-primary post-product-action" rel="tooltip" title="Edit Metadata"><span class="glyphicon glyphicon-pencil"></span></button> <button type="button" data-media-id="'+ jsonResponse.upload_data.media_id +'" data-action="delete" class="btn btn-xs btn-danger post-product-action" rel="tooltip" title="Delete"><span class="glyphicon glyphicon-trash"></span></button> </div> </div> </div><div class="media-preview" id="media-'+ jsonResponse.upload_data.media_id +'" data-id="'+ jsonResponse.upload_data.media_id +'"> <div class="media-thumbnail"> <div class="media-item margin-auto"> <img data-full-path="'+ image_pathFull +'" src="'+ image_pathNormal +'" class="product-image img-responsive animated fadeIn"> </div> </div> </div> </li>');

					MSBlog.doPostImageSort()

					var olArray = $('#add-image-wrapper').nestedSortable('toArray');
					// console.log(olArray)
					MSBlog.savePhotoOrder(olArray);
					// $('#'+file.id).replaceWith(jsonResponse.preview)

					// var featuredPath = $('#add-image-wrapper li').eq(0).find('.product-image').data('full-path');
					// $('#product-featured-photo').html( '<img class="img-responsive margin-auto animated fadeIn" src="'+featuredPath+'" />' )
					
				};
				if ( !jsonResponse.is_valid ) {
					$('#'+file.id).replaceWith('<li id="'+ file.id +'" class="error-file col-lg-3 col-md-4 col-xs-6 col-small-gutter error-file"><div class="media-preview file-detail"><span class="file_name">'+ file.name + '</span> <span class="file_size">('+ plupload.formatSize(file.size) +')</span>' + '<div class="text-warning">'+ jsonResponse.errors +'</div>' +'</div></li>');
				};

			});

			
			$dropElement.on({
			    "dragenter": function(){
			        $(this).addClass('mouse_over');
			    },
			    "mouseleave drop": function(){
			        $(this).removeClass('mouse_over');
			    }
			});

		},

		post_product_msis: function () {
			
			var $fileList = $('#add-image-wrapper').msis({
				ajaxOptions:{
					data: {
						ajax: 1,
						tpl: 'tpl',
						size: 'normal',
						attach_id: currentURL.query.id,
					}
				},
				errorMessage: '',
				errorClass: 'col-md-8',
				loader_image: base_url + 'img/loader.gif',
				trigger_type: "button",
				// load_more_class: "btn btn-default",

				created: function (el) {
					// console.log(el.attr('id'))

					// el.trigger('get_contents');
				},
				responseError: function (e, el, response) {
					// console.log(response)
				},
				done: function (e, el, response) {
					// console.log(el);
					// console.log(response);
					// console.log("doooonnnee");
					if ( typeof response != "undefined" && typeof response.total_rows_formatted != "undefined" ) {
						$('.msis-count').text(response.total_rows_formatted);
					};

					MSBlog.doPostImageSort()
					
				}
			});

		},

		postImage: function () {
			var	$dropElement = $('#image-wrapper')
			,	max_file_count = 1;

			if ( $dropElement.length == 0 ) return;

			var $currentImageWrap = $('#current-image-wrap');
			var $preloader = $('#post-image-preloader');
			var $postImagePickfile = $('#post-image-pickfile');

			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'post-image-pickfile', // you can pass an id...
				drop_element: 'image-wrapper',
				dragdrop: true,
				container: document.getElementById('post-image-pickfiles-container'), // ... or DOM Element itself
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
			        "attachment_for" : "post_image",
			        "resize" : 1,
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
				var jsonResponse = $.parseJSON(response.response);

				console.log(jsonResponse)

				if ( jsonResponse.is_valid ) {
					$('.msis-error-message-wrapper').hide();

					$dropElement.addClass('has-image');
					$postImagePickfile.html('<h1 class="mar0"><span class="icon ion-upload"></span></h1> Change Image');

					$('#post_image').val( jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name );
					$currentImageWrap.html( '<img class="img-responsive margin-auto animated fadeIn" src="'+ jsonResponse.image_path.medium  +'">' );
					
					uploader.removeFile(file.id);

					// submit the logo-title-form
					// $('#logo-title-form').submit();

				};

				if ( !jsonResponse.is_valid ) {
					$postImagePickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Add a logo');
					$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
					uploader.refresh();
				};

			});



		},

		post_product_action: function () {
			
			$('.post-product-action').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				if ( $this.data('action') == "edit" ) {

				};
				if ( $this.data('action') == "delete" ) {
					bootbox.dialog({
					  title: "Delete Confirmation",
					  message: "Are you sure you wish to delete the photo?",
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
					    	MSBlog.deleteProductImage([$this.data('media-id')]);

					      }
					    },
					  }
					});
					
				};


			});

		},

		deleteProductImage: function (media_ids) {
			
			if ( media_ids == null && media_ids.length == 0 ) return;

			console.log(media_ids);
			$.ajax({
				url: base_url + 'cms/media/delete',
				type: 'POST',
				dataType: 'json',
				data: {
					ajax: 1,
					media_ids: media_ids,
				},
			})
			.fail(function(e) {
				console.log(e.responseText);
				console.log("error");
			})
			.always(function(data) {
				console.log(data);
				if ( data !=null && data.length !=0 && data.is_valid ) {
					
					// remove all deleted on the list
					$.each(media_ids, function(index, val) {
						$('#post-product-'+ val).remove();
					});

					webArtJs.messenger({
						message: "Media Item/s has been deleted.",
						closeAfter: 3000,
						position: 'top-left',
						closeBtn: 1,
					})
				}else{
					webArtJs.messenger({
						header: "Oooops!",
						message: "Error deleting the file.",
						closeAfter: 3000,
						position: 'top-left notif-danger',
						closeBtn: 1,
					});
				}
				// console.log("complete");
			});	
		},

		doPostImageSort: function () {
			
			if ( typeof $.fn.nestedSortable == 'undefined' ) return;

	    	if ( $('#add-image-wrapper').length == 0 ) return;

			$('#add-image-wrapper').nestedSortable({
				// handle: 'div',
				items: 'li',
				// toleranceElement: '> div',
				maxLevels: 1,
				relocate: function () {
					// alert("relocate")
					var olArray = $('#add-image-wrapper').nestedSortable('toArray');
					// console.log(olArray)
					MSBlog.savePhotoOrder(olArray);
				}
			}); 

		},

		savePhotoOrder: function (olArray) {
			
			console.log(olArray)
			$.ajax({
				url: base_url + 'cms/blog/save_photo_order',
				type: 'POST',
				dataType: 'json',
				data: {
	            	photos: olArray,
	            },
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
				if ( data.is_valid ) {
					webArtJs.messenger({
						header: "",
						message: 'Product photo order has been saved',
						closeAfter: 3000,
						position: "small top-left",
						closeBtn: true,
					});
				};

				console.log("complete");
			});
				

		},

		blogCategory: function () {
	      console.log("blogCategory")
	      var selectProgram = 0;

			var $select = $('#category_id').selectize({
				create: true,
				createOnBlur: true,
			    // sortField: 'text',
			    onOptionAdd: function (value) {
			    	
			    	if ( selectProgram ) return;

			    	var control = $select[0].selectize;

			    	bootbox.dialog({
					  title: "Add Category Confirmation",
					  message: "Are you sure you wish to add " + value + " in category?",
					  // size: "small",
					  buttons: {
					    cancel: {
					      label: "Cancel",
					      className: "btn-default btn-sm",
					      callback: function() {
					      	control.removeOption(value, true)
					      	control.refreshItems()
				      		control.focus()
					      }
					    },
					    success: {
					      label: "Confirm",
					      className: "btn-primary btn-sm",
					      callback: function() {
					    	console.log("add "+ value + " to DB")
					    	MSBlog.addNewCategory(value, function (data) {
					    		console.log(data)
					    		if ( data.is_valid ) {			
					    			selectProgram = 1;
							      	control.removeOption(value, true)
							      
					    			control.addOption({
										value: data.id,
										text: value,
									});
									control.addItem(data.id);
									control.refreshItems();

									selectProgram = 0;
					    		};
					    	});
					      }
					    },
					  }
					});
			    },
			    onOptionRemove: function (value) {
			    	console.log(value)

			    	// var control = $select[0].selectize;
			    	// control.focus();
			    }

			});
			
			

	    	

			// $('#blog_category2').selectize({
			// 	create: true,
			// 	plugins: ['remove_button', 'drag_drop'],
			// 	persist: false,
			// })
	    },

	    addNewCategory: function (value, callback) {
	    	
	    	$.ajax({
	    		url: base_url + 'cms/blog/validate',
	    		type: 'POST',
	    		dataType: 'json',
	    		data: {
	    			title: value,
	    			body: '',
	    			type: 'blog_category',
	    			parent: currentURL.query.page_id,
	    			status: 'published',
	    		},
	    	})
	    	.fail(function(e) {
	    		console.log(e.responseText)
	    		console.log("error");
	    	})
	    	.always(callback);
	    		

	    },

	    tags: function () {
	    	
	    	console.log("tags")
	    	var selectProgram = 0;

	    	var $tags = $('#tags').selectize({
				create: true,
				createOnBlur: true,
				plugins: ['remove_button', 'restore_on_backspace'],
			});

	    },

	};

	MSBlog.init();

});
