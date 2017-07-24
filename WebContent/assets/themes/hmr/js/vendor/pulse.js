$(document).ready(function() {
	
	var base_url = $('#base_url').val()
	,	$dashMainContainer = $('#dash-main-container')
	,	$dashPageNav = $('#pages-navigation-wrap')
	,	$addPostForm = $('#add-post-form')
	,	is_query = 0
	,	somethingChanged = 0
	,	currentURL = new Url;

	var totalRespondents = 0;

	var RTLPulse = {
		init: function() {
			this.bindEvents();
			this.compute();
			this.post();
			this.addReportForm();
			this.locationChart();
			this.inputPercent();
			this.search();
		},
		uniqueID: function() {
			return Math.round(new Date().getTime() + (Math.random() * 100));
		},
		locationChart: function() {
			
			if ( $('#location-chart').length == 0 ) return;

			var given_data_label = [];
			var given_data = [];
			var data_series = [];

			$.each($('#location-section .location-item'), function(li, loci) {
				var $this = $(this);
				given_data.push($this.find('.percent_value').val())
				given_data_label.push($this.find('.location-title').val());
			});

			// console.log(given_data)
			$.each(given_data, function(index, val) {
				if ( index == 0 ) {
					var new_data = [0, val, 0];
				}else{
					var tempArr = [];
					for (var i = 0; i < index + 3; i++) {
						tempArr.push(0);
					}
					tempArr[index + 1] = val;
					new_data = tempArr;
				}
				data_series.push(new_data);
			});

			// console.log(data_series);



			var myChart = new Chartist.Line('#location-chart', {
			  series: data_series
			}, {
				width: 550,
				height: 200,
			  low: 0,
			  showPoint: 1,
			  showArea: 1,
			  showLine: 0,
			  fullWidth: 1,
			  chartPadding: {
			      top: 60,
			      right: 0,
			      bottom: 0,
			      left: 0
			  },
			  axisX: {
			  	offset: 0,
			    showGrid: false,
			    scaleMinSpace: 0,
			    showLabel: 0,
			  },
			  axisY: {
			  	offset: 0,
			    showGrid: false,
			    scaleMinSpace: 0,
			    showLabel: 0,
			  },
			});


			var seriesIndex;

			myChart.on('draw', function(data) {

				// if ( seriesIndex == data.seriesIndex ) return;
				// seriesIndex = data.seriesIndex;

			  if(data.type === 'point') {
			  	// console.log(data)

			  	var pointValue = Math.max.apply(Math, data.series);
			  	// console.log(pointValue)

			  	if ( data.value.y !=0 ) {
			  		var smashingFoob = data.element.parent().foreignObject('<div class="location-percent-value tac">'+ pointValue + '%<div class="location-label">'+ given_data_label[data.seriesIndex] +'</div></div>', {
				      width: 80,
				      height: 120,
				      x: data.x - 40,
				      y: data.y - 60
				    });

				    data.element.replace(smashingFoob);
			  	}else{
			  		var blank = data.element.parent().foreignObject('<div class="location-percent-value blank-pointer"></div>');
				    data.element.replace(blank);
			  	}

			    
			  }
			});

		},
		addReportForm: function() {
			
			// if ( $('#report_date_wrap').length == 0 ) return;

			// $('#report_date_wrap .input-group.date').datepicker({
			// 	autoclose: true,
			//     todayHighlight: true
			// });
			

			var $reportForm = $('#add-report-form');
			var $insightSection = $('#insight-section');
			var $btnAddMoreInsight = $('.btn-add-more-insight');
			var $locationSection = $('#location-section');
			var $btnAddMoreLocation = $('.btn-add-more-location');

			var $ageSection = $('#age-section');
			var $btnAddMoreAge = $('.btn-add-more-age');

			var $affiliationSection = $('#affiliation-section');
			var $btnAddMoreAffiliation = $('.btn-add-more-affiliation');

			var $lifeStageSection = $('#life-stage-section');
			var $btnAddMoreLifeStage = $('.btn-add-more-life-stage');

			var $insightItemTpl = $('.insight-item').eq(0).html();
			var $locationItemTpl = $('.location-item').eq(0).html();
			var $ageItemTpl = $('.age-item').eq(0).html();
			var $affiliationItemTpl = $('.affiliation-item').eq(0).html();
			var $lifeStageItemTpl = $('.lifestage-item').eq(0).html();

			var $clearSearchBtn = $('.clear-illustration-search-wrap');
			var $searchQ = $('#illustration-search-input');
			var $illustrationSearchForm = $('#illustration-search-form');


			RTLPulse.initVastPieGraph();

			$btnAddMoreInsight.on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				var insightCount = RTLPulse.uniqueID();
				// console.log(insightCount);

				$insightSection.append('<div id="insight-item-'+ insightCount +'" class="insight-item">'+ $insightItemTpl +'</div>');

				var $newInsightItem = $('#insight-item-'+ insightCount);

				$newInsightItem.find('.insight-title').focus();
				$newInsightItem.find('.insight-remove-item').data('insight-id', 0);
				$newInsightItem.find('.form-control').val('');
				$newInsightItem.find('.illustration-preview-wrap').html('<span class="icon ion-plus-round"></span><div class="icon-label">Illustration</div>');
			});

			$btnAddMoreLocation.on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				var locationCount = RTLPulse.uniqueID();
				// console.log(locationCount);

				$locationSection.append('<div id="location-item-'+ locationCount +'" class="location-item">'+ $locationItemTpl +'</div>');

				var $newlocationItem = $('#location-item-'+ locationCount);

				$newlocationItem.find('.location-title').focus();
				$newlocationItem.find('.location-remove-item').data('location-id', 0);
				$newlocationItem.find('.form-control').val('');
			});

			$btnAddMoreAge.on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				var ageCount = RTLPulse.uniqueID();
				// console.log(ageCount);

				$ageSection.append('<div id="age-item-'+ ageCount +'" class="age-item form-list-item">'+ $ageItemTpl +'</div>');

				var $newageItem = $('#age-item-'+ ageCount);

				$newageItem.find('.age-title').focus();
				$newageItem.find('.age-remove-item').data('age-id', 0);
				$newageItem.find('.form-control').val('');
			});

			$btnAddMoreAffiliation.on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				var affiliationCount = RTLPulse.uniqueID();
				// console.log(affiliationCount);

				$affiliationSection.append('<div id="affiliation-item-'+ affiliationCount +'" class="affiliation-item form-list-item">'+ $affiliationItemTpl +'</div>');

				var $newaffiliationItem = $('#affiliation-item-'+ affiliationCount);

				$newaffiliationItem.find('.affiliation-title').focus();
				$newaffiliationItem.find('.affiliation-remove-item').data('affiliation-id', 0);
				$newaffiliationItem.find('.form-control').val('');
			});

			$btnAddMoreLifeStage.on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				var lifeStageCount = RTLPulse.uniqueID();
				// console.log(lifeStageCount);

				$lifeStageSection.append('<div id="lifestage-item-'+ lifeStageCount +'" class="lifestage-item form-list-item">'+ $lifeStageItemTpl +'</div>');

				var $newLigeStageItem = $('#lifestage-item-'+ lifeStageCount);

				$newLigeStageItem.find('.lifestage-title').focus();
				$newLigeStageItem.find('.lifestage-remove-item').data('lifestage-id', 0);
				$newLigeStageItem.find('.form-control').val('');
			});

			$('.media-grid > li').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				if ( $this.hasClass('selected-icon') ) {
					$('.selected-icon').removeClass('selected-icon');
				}else{
					$('.selected-icon').removeClass('selected-icon');
					$this.addClass('selected-icon');	
				}
			});
			

			

			$illustrationSearchForm.on('submit', function(e) {
				e.preventDefault();
				var searchQValue = $searchQ.val();

				$.each($('#filelist').data(), function (i) {
				    $("#filelist").removeAttr("data-" + i);
				});

				$('#filelist-row').html('<ul id="filelist" class="media-grid" data-attachment-for="report_icon" data-url="'+ base_url + 'cms/media/q?q=' + searchQValue +'" data-trigger-type="button"></ul>');
				msMedia.msis();

				if ( $.trim(searchQValue).length !=0 ) {
					$clearSearchBtn.parents('.form-group').removeClass('hide');
				}else{
					$clearSearchBtn.parents('.form-group').addClass('hide');
				}
			});

			$clearSearchBtn.on('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				$searchQ.val('')
				$illustrationSearchForm.submit();

			});

			$('.add-icon-wrap a').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				$('.selected-icon-wrap').removeClass('selected-icon-wrap');
				$this.addClass('selected-icon-wrap');
			});

			$('#report-illustration-modal').on('hidden.bs.modal', function() {
				$('.selected-icon-wrap').removeClass('selected-icon-wrap');
				$('.selected-icon').removeClass('selected-icon');
				
				// CLEAR CUSTOMIZE ICON TAB
				$('#custom_icon_value').val('');
				$('#add_donut_chart').removeAttr('checked')
				$('#add_percent_sign').removeAttr('checked')
				$('#custom-value-preview').html('');
				$('#custom-chart-preview').html('');

				// CLEAR SEARCH FIELD
				if ( $.trim($searchQ.val()).length !=0 ) {
					$clearSearchBtn.click();	
				}
			});


			var donutCount = $('.vast-donut-chart').length;

			$('.btn-select-illustration').on('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				var $selectedIconWrap = $('.selected-icon-wrap');

				if ( $('#insert-icon-tab').hasClass('active') && $('.media-grid > li.selected-icon').length !=0 ) {
					var $img = $('.media-grid > li.selected-icon').find('img');
					$selectedIconWrap.find('.illustration-preview-wrap').html('<img class="img-responsive margin-auto" src="'+ $img.attr('src') +'" />');
					$selectedIconWrap.parents('.add-icon-wrap').find('.insight_icon').val( $img.data('original-path') );
				}

				if ( $('#customized-icon').hasClass('active') && $.trim($('#custom_icon_value').val()).length !=0 ) {
					var valuePreview = $.trim($('#custom_icon_value').val());

					$selectedIconWrap.parents('.add-icon-wrap').find('.custom_icon_value').val( valuePreview );
					$selectedIconWrap.parents('.add-icon-wrap').find('.add_percent').val( $('#add_percent_sign').val() );
					$selectedIconWrap.parents('.add-icon-wrap').find('.add_donut_chart').val( $('#add_donut_chart').val() );

					donutCount += $('.vast-donut-chart').length++;
					$selectedIconWrap.find('.illustration-preview-wrap').html('<div class="p-donut-label">'+ $('#custom-value-preview').text() +'</div>' + '<div class="ct-chart vast-donut-chart" id="custom-chart-preview-'+ donutCount +'"></div>' );

					if ( $('#add_donut_chart').is(":checked") ) {
						valuePreview = valuePreview.replace('%', '');
						RTLPulse.generatePieChart(valuePreview, '#custom-chart-preview-'+ donutCount);
					}



					// $selectedIconWrap.parents('.add-icon-wrap').find('.insight_icon').val( $img.data('original-path') );
				}

				console.log( $('.selected-icon-wrap').parents('.add-icon-wrap').find('.insight_icon').val() )

			});


			$('.insight-remove-item').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				bootbox.dialog({
					title: "Removal Confirmation",
					message: "Are you sure you want to remove the insight?",
					buttons: {
						remove: {
					      label: "Remove",
					      className: "btn-sm btn-danger",
					      callback: function () {
					      	if ( $this.data('insight-id') !=0 ) {
					      		RTLPulse.deleteInsight($this.data('insight-id'));
					      	}
					      	$this.parents('.insight-item').remove();
					      }
					    },
					    dismiss: {
					      label: "Dismiss",
					      className: "btn-sm btn-default",
					      callback: function () {
					      	
					      }
					    }
					}
				})
			});

			$('.location-remove-item').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				bootbox.dialog({
					title: "Removal Confirmation",
					message: "Are you sure you want to remove the location?",
					buttons: {
						remove: {
					      label: "Remove",
					      className: "btn-sm btn-danger",
					      callback: function () {
					      	if ( $this.data('location-id') !=0 ) {
					      		RTLPulse.deleteLocation($this.data('location-id'));
					      	}
					      	$this.parents('.location-item').remove();
					      }
					    },
					    dismiss: {
					      label: "Dismiss",
					      className: "btn-sm btn-default",
					      callback: function () {
					      	
					      }
					    }
					}
				})
			});

			$('.age-remove-item').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				bootbox.dialog({
					title: "Removal Confirmation",
					message: "Are you sure you want to remove the age?",
					buttons: {
						remove: {
					      label: "Remove",
					      className: "btn-sm btn-danger",
					      callback: function () {
					      	console.log($this.data('age-id'))
					      	if ( $this.data('age-id') !=0 ) {
					      		RTLPulse.deleteAge($this.data('age-id'));
					      	}
					      	$this.parents('.age-item').remove();
					      }
					    },
					    dismiss: {
					      label: "Dismiss",
					      className: "btn-sm btn-default",
					      callback: function () {
					      	
					      }
					    }
					}
				})
			});

			$('.affiliation-remove-item').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				bootbox.dialog({
					title: "Removal Confirmation",
					message: "Are you sure you want to remove the affiliation?",
					buttons: {
						remove: {
					      label: "Remove",
					      className: "btn-sm btn-danger",
					      callback: function () {
					      	console.log($this.data('affiliation-id'))
					      	if ( $this.data('affiliation-id') !=0 ) {
					      		RTLPulse.deleteAffiliation($this.data('affiliation-id'));
					      	}
					      	$this.parents('.affiliation-item').remove();
					      }
					    },
					    dismiss: {
					      label: "Dismiss",
					      className: "btn-sm btn-default",
					      callback: function () {
					      	
					      }
					    }
					}
				})
			});

			$('.lifestage-remove-item').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);
				bootbox.dialog({
					title: "Removal Confirmation",
					message: "Are you sure you want to remove the lifestage?",
					buttons: {
						remove: {
					      label: "Remove",
					      className: "btn-sm btn-danger",
					      callback: function () {
					      	console.log($this.data('lifestage-id'))
					      	if ( $this.data('lifestage-id') !=0 ) {
					      		RTLPulse.deleteLifeStage($this.data('lifestage-id'));
					      	}
					      	$this.parents('.lifestage-item').remove();
					      }
					    },
					    dismiss: {
					      label: "Dismiss",
					      className: "btn-sm btn-default",
					      callback: function () {
					      	
					      }
					    }
					}
				})
			});




			// CUSTOMIZE ICON
			var $customValueWrap = $('#custom-value-preview');
			$('#custom_icon_value').on('change keyup', function() {
				var valuePreview = $.trim($(this).val());

				if ( $('#add_percent_sign').is(":checked") ) {
					valuePreview = valuePreview + '%';
				}
				$customValueWrap.text( valuePreview  );

				if ( $('#add_donut_chart').is(":checked") ) {
					valuePreview = valuePreview.replace('%', '');
					RTLPulse.generatePieChart(valuePreview, '#custom-chart-preview');
				}
			});

			$('#add_percent_sign').on('change', function() {
				var valuePreview = $.trim($('#custom_icon_value').val());
				if ( $(this).is(':checked') ) {
					valuePreview = valuePreview + '%';
				}else{
					valuePreview = valuePreview.replace('%', '');
				}
				$customValueWrap.text( valuePreview  );	
			});

			$('#add_donut_chart').on('change', function() {
				if ( $(this).is(':checked') ) {
					var valuePreview = $.trim($('#custom_icon_value').val());
					valuePreview = valuePreview.replace('%', '');

					RTLPulse.generatePieChart(valuePreview, '#custom-chart-preview');
				}else{
					$('#custom-chart-preview').html('');
				}
			});


			$('.report-illustration-tab > li > a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
				$('.selected-icon').removeClass('selected-icon');
				console.log( $(e.target).attr('href') );
			  // e.relatedTarget // previous active tab
			});



			if ( $reportForm.length == 0 ) return;

			$reportForm.on('submit', function(e) {
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
					console.log("complete");

					if ( typeof data !="undefined" && data.is_valid ) {

						bootbox.dialog({
							title: "Congratulations!",
							message: "Post has been saved.",
							buttons: {
								dismiss: {
							      label: "Dismiss",
							      className: "btn-sm btn-default",
							      callback: function () {
							      	window.location = data.success_location;
							      }
							    }
							},
							onEscape: function() {
								window.location = data.success_location;
							}
						});

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
				});

			});



			
			// MSAdmin.generalSentiments();

		},

		initVastPieGraph: function() {
			// GENERATE ICON PREVIEW ON EDITING

			if ( $('.vast-donut-chart').length !=0 ) {
				$.each( $('.vast-donut-chart') , function(index, val) {
					var $this = $(this);

					if ( $this.data('value') && $this.attr('id') !=null ) {
						RTLPulse.generatePieChart($this.data('value'), '#'+ $this.attr('id'));
					}

				});
			}


		},

		deleteInsight: function(insight_id) {
		
			$.ajax({
				url: base_url + 'cms/pulse/delete_insight',
				type: 'POST',
				dataType: 'json',
				data: {insight_id: insight_id},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
			});

		},

		deleteLocation: function(location_id) {
		
			$.ajax({
				url: base_url + 'cms/pulse/delete_location',
				type: 'POST',
				dataType: 'json',
				data: {location_id: location_id},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
			});

		},

		deleteAge: function(age_id) {
		
			$.ajax({
				url: base_url + 'cms/pulse/delete_age',
				type: 'POST',
				dataType: 'json',
				data: {age_id: age_id},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
			});

		},

		deleteAffiliation: function(affiliation_id) {
		
			$.ajax({
				url: base_url + 'cms/pulse/delete_affiliation',
				type: 'POST',
				dataType: 'json',
				data: {affiliation_id: affiliation_id},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
			});

		},

		deleteLifeStage: function(lifestage_id) {
		
			$.ajax({
				url: base_url + 'cms/pulse/delete_lifestage',
				type: 'POST',
				dataType: 'json',
				data: {lifestage_id: lifestage_id},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(function(data) {
				console.log(data)
			});

		},

		generatePieChart: function(value, id) {
			
			if ( Math.abs(value) < 100 ) {
				positiveValue = Math.abs(value);
				negativeValue = 100 - positiveValue;
			}else{
				positiveValue = 100;
				negativeValue = 0;
			}

			if ( value < 0 ) {
				positiveValue = 100 - Math.abs(value);
				negativeValue = Math.abs(value);
			}

			console.log(positiveValue)

			var chart = new Chartist.Pie(id, {
				series: [
				{
					name: 'Positive',
					className: 'positive',
					value: positiveValue
				},
				{
					name: 'Negative',
					className: 'negative',
					value: negativeValue
				}]
			},
			{
				height: 100,
				width: 100,
				donutWidth: 6,
				donut: true,
				showLabel: false,
			});

			
			chart.on('draw', function(data) {

				// console.log(data)
				

				if(data.type === 'label') {
    
			        if(data.index === 0) {
			          data.element.attr({
			            dx: data.element.root().width() / 2,
			            dy: data.element.root().height() / 2
			          });
			        } else {
			          data.element.remove();
			        }
			      }

			      if(data.type === 'slice') {
			        // Get the total path length in order to use for dash array animation
			        var pathLength = data.element._node.getTotalLength();

			        // Set a dasharray that matches the path length as prerequisite to animate dashoffset
			        data.element.attr({
			          'stroke-dasharray': pathLength + 'px ' + pathLength + 'px'
			        });

			        // Create animation definition while also assigning an ID to the animation for later sync usage
			        var animationDefinition = {
			          'stroke-dashoffset': {
			            id: 'anim' + data.index,
			            dur: 1000,
			            from: -pathLength + 'px',
			            to:  '0px',
			            easing: Chartist.Svg.Easing.easeOutQuint,
			            // We need to use `fill: 'freeze'` otherwise our animation will fall back to initial (not visible)
			            fill: 'freeze'
			          }
			        };

			        // If this was not the first slice, we need to time the animation so that it uses the end sync event of the previous animation
			        if(data.index !== 0) {
			          animationDefinition['stroke-dashoffset'].begin = 'anim' + (data.index - 1) + '.end';
			        }

			        // We need to set an initial value before the animation starts as we are not in guided mode which would do that for us
			        data.element.attr({
			          'stroke-dashoffset': -pathLength + 'px'
			        });

			        // We can't use guided mode as the animations need to rely on setting begin manually
			        // See http://gionkunz.github.io/chartist-js/api-documentation.html#chartistsvg-function-animate
			        data.element.animate(animationDefinition, false);
			      }

			});

		    chart.on('created', function() {
		    	console.log("created")
		    })

		},
		
		compute: function() {
			
			RTLPulse.getTotalRespondents();

			$('.data-raw').live('keyup', function() {
				var $this = $(this);
				
				
				RTLPulse.updateForm($this.data('modified-id'));
				
				console.log("totalRespondents:"+ totalRespondents)
			});

			console.log("totalRespondents:"+ totalRespondents)
			
			RTLPulse.getModifiedItemTotals();

		},

		updateForm: function(modified_id) {
		
			RTLPulse.getTotalRespondents()
				
			RTLPulse.getModifiedItemTotals();
			RTLPulse.getNegativePositiveTotals(modified_id);

			RTLPulse.getPercentData();

			$('#negative-respondents-'+modified_id).data('total-respondents', 0);
			$('#positive-respondents-'+modified_id).data('total-respondents', 0);

			$('.modified-item').data('total-respondents',  0);
			$('.pulse-total-respondents').val(totalRespondents).text(totalRespondents);

		},

		getModifiedItemTotals: function() {
		
			$.each($('.modified-item'), function(index, val) {
				var $this = $(this);
				var $modifiedItem = $('#'+ $this.attr('id'));
				var modifiedID = $this.data('id');

				// TOTAL RESPONDENTS OF TITLE CATEGORY
				$.each( $('#'+ $this.attr('id') + ' .data-raw'), function(index, val) {
					var $dataRaw = $(this);
					

					var rawData = ($.trim($dataRaw.val()).length == 0) ? 0 : parseInt($dataRaw.val());
					var titleTotalRespondents = $modifiedItem.data('total-respondents') + rawData;
					
					$modifiedItem.data('total-respondents',  titleTotalRespondents);


					var title_category_percent = (totalRespondents !=0) ? (titleTotalRespondents / totalRespondents * 100).toFixed(2) + ' %' : '00.00 %';
					$('#percent-title-category-'+modifiedID).text(title_category_percent)

					$('#total-tite-category-respondents-'+ modifiedID).text( $modifiedItem.data('total-respondents') );

				});


			});

			$.each($('.modified-item'), function(index, val) {
				var $this = $(this);

				console.log('total: ' + $this.data('total-respondents') );

			});

		},

		getNegativePositiveTotals: function(modified_id) {

			// NEGATIVE AND POSITIVE QUERY
			$.each( $('#negative-tab-'+ modified_id + ' .data-raw'), function(index, val) {
				var $negaDataRaw = $(this);
				var $negativeRespondents = $('#negative-respondents-'+ $negaDataRaw.data('modified-id'));

				var negaRawData = ($.trim($negaDataRaw.val()).length == 0) ? 0 : parseInt($negaDataRaw.val());
				var NegativeTotalRespondents = $negativeRespondents.data('total-respondents') + negaRawData;

				$negativeRespondents.data('total-respondents', NegativeTotalRespondents);
				$negativeRespondents.text( NegativeTotalRespondents )


				var $negativeProgress = $('#progress-negative-' + modified_id);
				var titleTotalRespondents = $('#modified-'+modified_id).data('total-respondents');

				var total_negative = (NegativeTotalRespondents / titleTotalRespondents * 100).toFixed(2) + '%';
				$negativeProgress.css('width', total_negative).find('span').text(total_negative + ' Negative');

			});

			$.each( $('#positive-tab-'+ modified_id + ' .data-raw'), function(index, val) {
				var $posiDataRaw = $(this);
				var $positiveRespondents = $('#positive-respondents-'+ $posiDataRaw.data('modified-id'));

				var posiRawData = ($.trim($posiDataRaw.val()).length == 0) ? 0 : parseInt($posiDataRaw.val());
				var positiveTotalRespondents = $positiveRespondents.data('total-respondents') + posiRawData;

				$positiveRespondents.data('total-respondents', positiveTotalRespondents);
				$positiveRespondents.text( positiveTotalRespondents )


				var $positiveProgress = $('#progress-positive-' + modified_id);
				var titleTotalRespondents = $('#modified-'+modified_id).data('total-respondents');

				var total_positive = (positiveTotalRespondents / titleTotalRespondents * 100).toFixed(2) + '%';
				$positiveProgress.css('width', total_positive).find('span').text(total_positive + ' Positive');

			});


			RTLPulse.getTotalRespondents()

		},

		getPercentData: function() {
		
			
			$.each($('.data-raw'), function(index, val) {
				var $this = $(this);

				if ( currentURL.query.type == "Modified" ) {
					var titleTotalRespondents = $('#modified-'+$this.data('modified-id')).data('total-respondents');	
				}
				if ( currentURL.query.type == "Positive-and-Negative" || currentURL.query.type == "Hate-and-Want" || currentURL.query.type == "Say" ) {
					var titleTotalRespondents = totalRespondents;
				}

				
				var rawData = ($.trim($this.val()).length == 0) ? 0 : parseInt($this.val());
				var percent_data = (titleTotalRespondents !=0 && rawData !=0) ? (rawData / titleTotalRespondents * 100).toFixed(2) + ' %' : '00.00 %';

				console.log(percent_data)


				$this.parents('.data-group-wrap').find('.data-percent').text(percent_data);
			});

		},

		getTotalRespondents: function() {
			totalRespondents = 0;
			$.each($('.data-raw'), function(index, val) {
				var $this = $(this);
				var rawData = ($.trim($this.val()).length == 0) ? 0 : parseInt($this.val());
				totalRespondents = totalRespondents + rawData;
			});

		},

		bindEvents: function() {
			
		},

		search: function () {
			
			$('.post-search-form').on('submit', function (e) {
				e.preventDefault();
			 	var serializeArray = $(this).serializeArray()
				currentURL.query.q = serializeArray[0].value;
				delete currentURL.query.slug;
				if ( serializeArray[0].value == '' ) {
					delete currentURL.query.q;
				};
				window.location = currentURL.toString();
			});

		},

		inputPercent: function() {
		
			if ( $.fn.autoNumeric ) {
            	$('.price-percent').autoNumeric({
            		aSign: ' %',
            		pSign: 's',
            	});

            	$('.input-int').autoNumeric({
            		aSep: '',
            		vMin: 0,
            		mDec: 0
            	});

            	$('.input-decimal').autoNumeric({
            		aSep: '',
            		vMin: 0,
            		mDec: 2
            	});
        	};

		},

		post: function () {

			$('.pulse-form-wrap .form-control').live('change', function() { 
				somethingChanged = 1;
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

						// MSPB2.submitContent(function (pbdata) {
						// 	console.log(pbdata)
						// 	if ( pbdata.is_valid ) {
						// 		// location.reload();
						// 		somethingChanged = 0;
						// 	};
						// });

						somethingChanged = 0;

						webArtJs.messenger({
							message: "Post has been saved!",
							closeAfter: 3000,
							position: 'top-left',
							closeBtn: 1,
						});

						setTimeout(function () {
							window.location = base_url + 'cms/pulse/post?id='+ data.id + '&type=' + data.post_data.post_type;
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
			            		if ( MSAdmin.isValidSelector("#" + index) ) {
				            		var $navLi = $('#'+ $("#" + index).parents('.form-group').parents('.tab-pane').attr('id') + '-li' );
				            		$navLi.addClass('tab-has-error');
				            		$("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');	
			            		}
			            		if ( index == "content[]" || index == "data[]" || index == "title_category[]" ) {
			            			$('#breakdown-tab-li').addClass('tab-has-error');
			            			RTLPulse.checkBreakDownFields()

			            		}else{
			            			$('#breakdown-tab-li').removeClass('tab-has-error');
			            		}
			            	}
			            });

					}
					console.log("complete");
				});
					

			});

			$('.post-action').live('click', function (e) {
				e.preventDefault();
				var $this = $(this);

				if ( $this.data('type') == "Save" ) {
					$addPostForm.submit();
				}

				if ( $this.data('type') == "Cancel" ) {
					
					if ( somethingChanged == 0 ){
						window.location = base_url + 'cms/pulse';
						return;
					}
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
					    	window.location = base_url + 'cms/pulse';
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

					    	RTLPulse.setDelete($this.data('id'), function (data) {
					    		if ( data.is_valid ) {
					    			webArtJs.messenger({
										message: "Post has been deleted!",
										closeAfter: 3000,
										position: 'top-left',
										closeBtn: 1,
									});

									if ( $('#dash-stream-pulse-'+ $this.data('id')).length !=0 ) {
										$('#dash-stream-pulse-'+ $this.data('id')).remove();
										$( '#pulse-stream-iframe' ).attr( 'src', function ( i, val ) { return val; });

									}else{
										window.location = base_url + 'cms/pulse';
									}
									
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

			RTLPulse.tags();
			RTLPulse.blogCategory();
			RTLPulse.dateTimePicker();
			RTLPulse.postImage();
			RTLPulse.postBgImage();
			RTLPulse.breakdown();

		},

		checkBreakDownFields: function() {
		
			$.each($('.breakdown-container .form-control'), function(ii, vall) {
				var $this = $(this);
				if ( $.trim($this.val()).length == 0 ) {
					$this.parents('.form-group').addClass('has-error').parents('.pulse-content-form').find('.help-block').text('Percent and Setiment fields are required.').addClass('error-help-block');
				}

			});

		},

		breakdown: function() {
			RTLPulse.inputPercent();
			MSAdmin.autosizeTextArea();

			// PULSE NEGATIVE POSTIVE | HATE AND WANT
			$('.add-more-row').live('click', function(e) {
				e.preventDefault()
				var $this = $(this);

				console.log($this.data('slant'))
				if ( $this.data('slant') == "Negative" ) {
					var $bdRow = RTLPulse.render('pulse_breakdown',{
						slant: 'Negative',
						percent_value: '', // data
						data_type: 'Percent',
						parent_id: 0,
						modified_id: $this.data('modified-id'),
					});

					$('.negative-tab-rows').append($bdRow)
				}

				if ( $this.data('slant') == "positive" ) {
					var $bdRow = RTLPulse.render('pulse_breakdown',{
						slant: 'Positive',
						percent_value: '', // data
						data_type: 'Percent',
						parent_id: 0,
						modified_id: $this.data('modified-id'),
					});

					$('.positive-tab-rows').append($bdRow);
				}

				if ( $this.data('slant') == "Say" ) {
					var $bdRow = RTLPulse.render('pulse_breakdown',{
						slant: 'Say',
						percent_value: '', // data
						data_type: 'Percent',
						parent_id: 0,
						modified_id: $this.data('modified-id'),
					});

					$('.say-tab-rows').append($bdRow)
				}

				MSAdmin.bootstrapPlugins();

				RTLPulse.inputPercent();

				$($('.breakdown-container .ms-tabs > li.active > a').attr('href')).find('.price-percent').last().focus();

				MSAdmin.autosizeTextArea();

			});

			$('.add-more-row-sentiment').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				console.log($this.data('slant'))
				console.log($this.data('modified-id'))
				
				if ( $this.data('slant') == "Negative" ) {
					
					var $bdRow = RTLPulse.render('pulse_breakdown',{
						slant: 'Negative',
						percent_value: '', // data
						data_type: 'Percent',
						parent_id: $this.data('parent-id'),
						modified_id: $this.data('modified-id'),
					});

					$('.negative-tab-'+ $this.data('modified-id') +'-rows').append($bdRow)
				}

				if ( $this.data('slant') == "Positive" ) {
					
					var $bdRow = RTLPulse.render('pulse_breakdown',{
						slant: 'Positive',
						percent_value: '', // data
						data_type: 'Percent',
						parent_id: $this.data('parent-id'),
						modified_id: $this.data('modified-id'),
					});

					$('.positive-tab-'+ $this.data('modified-id') +'-rows').append($bdRow)
				}

				MSAdmin.bootstrapPlugins();
				RTLPulse.inputPercent();

				// $( '#'+ $this.data('slant').toLowerCase() + '-tab-' + $this.data('modified-id') ).find('.price-percent').last().focus();

				MSAdmin.autosizeTextArea();


			});

			$('.btn-remove-sentiment').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				bootbox.dialog({
				  title: "Delete Confirmation",
				  message: "Are you sure you wish to delete this sentiment?",
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

				      	console.log("btn-remove-sentiment:" + $this.data('id') );

				      	if ( typeof $this.data('id') !="undefined" && $this.data('id') !=0 ) {
				      		
				      		RTLPulse.deleteSentiment($this.data('id'), function(response) {
				      			if ( typeof response !="undefined" && response.is_valid ) {
				      				$this.parents('.pulse-content-form').remove();

				      				RTLPulse.updateForm($this.data('id'));

				      			}else{
				      				alert("If you seeing this frequently. Please contact your administrator");
				      			}
				      		});

				      	}else{
				      		$this.parents('.pulse-content-form').remove();
				      		RTLPulse.updateForm($this.parents('.pulse-content-form').data('modified-id'));
				      	}
				    	
				      }
				    },
				  }
				});

				

			});

			RTLPulse.addMoreModifiedItem();

		},

		deleteSentiment: function(id, callback) {
			
			return $.ajax({
				url: base_url + 'cms/pulse/delete_sentiment',
				type: 'POST',
				dataType: 'json',
				data: {id: id},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(callback);
					

		},

		addMoreModifiedItem: function() {
		
			$('.add-more-modified-tpl').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				$.ajax({
					url: base_url + 'cms/pulse/get_modified_item_tpl',
					type: 'GET',
					dataType: 'json',
					data: {
						ajax: 1,
						id: $this.data('id')
					},
				})
				.fail(function(e) {
					console.log(e.responseText)
					console.log("error");
				})
				.always(function(data) {
					console.log(data)
					if ( typeof data !="undefined" && typeof data.tpl !="undefined" ) {
						$('#modified-list-wrap').append(data.tpl)
					}

					
					MSAdmin.bootstrapPlugins();
					RTLPulse.inputPercent()
					MSAdmin.autosizeTextArea();

					// RTLPulse.breakdown();
					console.log("complete");
				});
					

			});

			$('.btn-remove-modified-item').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				bootbox.dialog({
				  title: "Delete Confirmation",
				  message: "Are you sure you wish to delete this title category? <br> <br> <small>Note: All the sentiments under this Category will also be deleted. </small>",
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
				    	
				    	RTLPulse.deletePulseCategory($this.data('id'), function(data) {
				    		console.log(data)
				    		if ( data.is_valid ) {
				    			$('#modified-'+ $this.data('id')).remove();	
				    		}else{
				    			webArtJs.messenger({
									message: "There's a problem deleting the post. Please try again later.",
									closeAfter: 3000,
									position: 'top-left',
									closeBtn: 1,
								});
				    		}
				    	})
				      }
				    },
				  }
				});

			});

		},

		deletePulseCategory: function(id, callback) {
		
			return $.ajax({
				url: base_url + 'cms/pulse/delete_pulse_category',
				type: 'POST',
				dataType: 'json',
				data: {id: id},
			})
			.fail(function(e) {
				console.log(e.responseText)
				console.log("error");
			})
			.always(callback);

		},

		setDelete: function(id, callback) {
			
			return $.ajax({
				url: base_url + 'cms/pulse/set_deleted',
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

			RTLPulse.datePostedText(formattedDate);

			$blogDatePicker.on('changeDate', function(e) {
				var $this = $(this);
				formattedDate = $this.datepicker('getFormattedDate');
				var date_arr = formattedDate.split('-');

		        $postDate.val( formattedDate )
		        
		        console.log( formattedDate )	

		        // console.log( moment(formattedDate).calendar() )

		        RTLPulse.datePostedText(formattedDate);

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
		    	RTLPulse.datePostedText(formattedDate);
		    }

		    
		    $('#status').on('change', function() {
		    	var $this = $(this);
		    	if ( $this.find('option:selected').val() == "published" ) {
		    		RTLPulse.datePostedText(formattedDate);
		    	}
		    	if ( $this.find('option:selected').val() == "draft" ) {
		    		$dateTimeText.html("This post will not be visible.");
		    	}
		    	if ( $this.find('option:selected').val() == "scheduled" ) {
		    		RTLPulse.datePostedText(formattedDate);
		    	}
		    });

		    $(document).click(function(e){
			    if ($(e.target).closest(".datetime-pick").length == 0 && $(e.target).closest(".ms-datetime-picker").length == 0 ) {
			        $('#post-datetime-wrap').addClass('hide');
			    }
			});

			$('#post-minutes').on('change', function() {
				var $this = $(this);
				if ( parseInt($this.val()) > 12 ) {
					$this.val(12);
				}
				if ( parseInt($this.val()) == 0 ) {
					$this.val(1)
				}
				if ( $this.val() < 10 ) {
					$this.val( '0' + parseInt($this.val()) );
				}
			}).on('keyup, change', function() {
				RTLPulse.datePostedText(formattedDate);
			})

			$('#post-seconds').on('change', function() {
				var $this = $(this);
				console.log($this.val())
				console.log( parseInt($this.val()) )
				if ( parseInt($this.val()) > 59 ) {
					$this.val(59);
				}
				if ( parseInt($this.val()) <= 0 ) {
					$this.val(00)
				}
			}).on('keyup, change', function() {
				var $this = $(this);
				if ( $this.val() < 10 ) {
					$this.val( '0' + parseInt($this.val()) );
				}
				RTLPulse.datePostedText(formattedDate);
			})

			$('#post-meridian').on('change', function() {
				RTLPulse.datePostedText(formattedDate);	
			});

		},

		datePostedText: function(formattedDate) {
			
			console.log("datePostedText")
			var $dateTimeText = $('#datetime-text');
			var now = moment().format('YYYY-MM-DD');

		    console.log(formattedDate)

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

	        if ( diff >=1 ) {
	        	$('#status').val('scheduled');
	        }else{
	        	$('#status').val('published');
	        }



	        

	        if ( $('#status').val() == "publish" && diff < 0 ) {
				$dateTimeText.html( '<span class="post-stat-wrap">Published</span> on <a href="#" class="datetime-pick"> '+ moment(datePicked).format("MMM D") +' at ' + time + '</a>');	
			}
		},

		blogCategory: function () {
	      console.log("blogCategory")
	      var selectProgram = 0;

	      	if ( $('#category_id').length == 0 ) return;
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
					    	RTLPulse.addNewCategory(value, function (data) {
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

	    	if ( $('#tags').length == 0 ) return;

	    	var $tags = $('#tags').selectize({
				create: true,
				createOnBlur: true,
				plugins: ['remove_button', 'restore_on_backspace'],
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
				
				
				// console.log(file.percent)

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

					$('.post-image-remove').removeClass('hide');

					$('#post_image').val( jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name );
					$currentImageWrap.html( '<img class="img-responsive margin-auto animated fadeIn" src="'+ jsonResponse.image_path.medium  +'">' );
					
					uploader.removeFile(file.id);

					// submit the logo-title-form
					// $('#logo-title-form').submit();

				};

				if ( !jsonResponse.is_valid ) {
					$postImagePickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Add an image');
					$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
					uploader.refresh();
				};

			});

			$('.post-image-remove').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				bootbox.dialog({
					  title: "Delete Confirmation",
					  message: "Are you sure you wish to delete this thumbnail?",
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
							
							$('#post_image').val('');

							$dropElement.removeClass('has-image');
							$postImagePickfile.html('<h1 class="mar0"><span class="icon ion-upload"></span></h1> Add an image');
							$currentImageWrap.html('');
							$this.addClass('hide')
					    	
					      }
					    },
					  }
					});


				

			});



		},

		postBgImage: function () {
			var	$dropElement = $('#background-image-wrapper')
			,	max_file_count = 1;

			if ( $dropElement.length == 0 ) return;

			var $currentImageWrap = $('#background-current-image-wrap');
			var $preloader = $('#background-image-preloader');
			var $backgroundImagePickfile = $('#background-image-pickfile');

			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				browse_button : 'background-image-pickfile', // you can pass an id...
				drop_element: 'background-image-wrapper',
				dragdrop: true,
				container: document.getElementById('background-image-pickfiles-container'), // ... or DOM Element itself
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
			        "attachment_for" : "pulse_background_image",
			        "resize" : 1,
			    }
			});
			// console.log(uploader);

			uploader.init();

			// UploadProgress
			uploader.bind('UploadProgress', function (up, file) {
				$('#'+file.id).find('.progress-bar').css('width', file.percent + '%').find('.sr-only').text(file.percent + '%');
				
				
				// console.log(file.percent)

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
					$backgroundImagePickfile.html('<h1 class="mar0"><span class="icon ion-upload"></span></h1> Change Image');

					$('.background-image-remove').removeClass('hide');

					$('#background_image').val( jsonResponse.upload_data.upload_full_path + '/' + jsonResponse.upload_data.file_name );
					$currentImageWrap.html( '<img class="img-responsive margin-auto animated fadeIn" src="'+ jsonResponse.image_path.medium  +'">' );
					
					uploader.removeFile(file.id);

					// submit the logo-title-form
					// $('#logo-title-form').submit();

				};

				if ( !jsonResponse.is_valid ) {
					$backgroundImagePickfile.html('<h1 class="upload-icon mar0"><span class="icon ion-upload"></span></h1> Add an image');
					$preloader.replaceWith('<div id="'+ file.id +'" class="error-file"><div class="media-preview file-detail">' + '<div class="text-danger">'+ jsonResponse.errors +'</div>' +'</div></div>');
					uploader.refresh();
				};

			});

			$('.background-image-remove').live('click', function(e) {
				e.preventDefault();
				var $this = $(this);

				bootbox.dialog({
					  title: "Delete Confirmation",
					  message: "Are you sure you wish to delete this thumbnail?",
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
							
							$('#post_image').val('');

							$dropElement.removeClass('has-image');
							$postImagePickfile.html('<h1 class="mar0"><span class="icon ion-upload"></span></h1> Add an image');
							$currentImageWrap.html('');
							$this.addClass('hide')
					    	
					      }
					    },
					  }
					});


				

			});



		},

		render : function(template,params){
			var arr = [];
			switch(template){
				case 'pulse_breakdown':

					arr = ['<div data-modified-id="'+ params.modified_id +'" data-content-id="0" data-slant="'+params.slant+'" class="pulse-content-form '+ params.slant +'">',
								'<a href="#" tabindex="-1" class="btn-remove-sentiment" data-toggle="tooltip" data-placement="left" title="Remove Sentiment"><span class="ion-backspace"></span></a>',
								'<input type="hidden" name="parent_id[]" value="'+params.parent_id+'">',
								'<input type="hidden" name="slant[]" value="'+params.slant+'">',
								'<input type="hidden" name="data_type[]" value="'+params.data_type+'">',
								'<div class="row small-gutter data-group-wrap">',
									'<div class="col-sm-2">',
										'<div class="form-group">',
											'<label class="data-percent">00.00 %</label>',
										'</div>',
									'</div>',
									'<div class="col-sm-3">',
										'<div class="form-group">',
											'<input data-modified-id="'+ params.modified_id +'" name="data[]" type="text" min="0" class="form-control data-raw input-int" value="" required>',
										'</div>',
									'</div>',
									'<div class="col-sm-7">',
										'<div class="form-group">',
											'<textarea name="content[]" class="form-control autosize-ta" placeholder="'+ params.slant +' Sentiments" required></textarea>',
											'<div class="help-block text-danger"></div>',
										'</div>',
									'</div>',
								'</div>',

							'</div>'];
				break;

			}		
			return arr.join('');
			
		},






	};


	RTLPulse.init();

});		