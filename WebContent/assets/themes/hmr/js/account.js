$(document).ready(function() {
	
    var base_url = $('#base_url').val();
    var $subscriptionForm = $('#customers-subscription-form');
    var $tabValidation = $('#tab-validation');
    var unit_price = 99;

	// Called when token created successfully.
    var successCallback = function(data) {

        $('#token').val(data.response.token.token);
        console.log(data.response.token.token);
        $subscriptionForm.submit();
    };

    // Called when token creation fails.
    var errorCallback = function(data) {
        console.log(data)
        if (data.errorCode === 200) {
            tokenRequest();
        }else {
            // alert(data.errorMsg);

            bootbox.dialog({
                title: "Error occurs!",
                message: data.errorMsg,
                buttons: {
                    dismiss: {
                      label: "Dismiss",
                      className: "btn-sm btn-default",
                      callback: function () {
                        
                      }
                    }
                }
            });
        }
    };

    var tokenRequest = function() {
        // Setup token request arguments
        var args = {
            // sellerId: "901324533",
            // publishableKey: "2FC98B1F-7AFF-465C-B63C-96F6CEADE139", //kevincarpio

            sellerId: "202723376",
            publishableKey: "07A4E463-81E0-4353-8DE7-DC8DA3D24F6D", //politicks
            ccNo: $("#ccNo").val(),
            cvv: $("#cvv").val(),
            expMonth: $("#expMonth").val(),
            expYear: $("#expYear").val()
        };

        // Make the token request
        TCO.requestToken(successCallback, errorCallback, args);
    };

    var bindEvents = function() {

        $('#total_summary').val( unit_price );

        $('#no_of_months').on('change', function(e) {
           var $this = $(this);
           if ( $this.val() == 0 ) {
            $this.val(1);
           }

           $('#no_of_months_summary').val( $this.val() );
           var numberOfMonths = parseInt($this.val());
           var total = unit_price * numberOfMonths;

           $('#no_of_months_summary').val( $this.val() )
           $('#total_summary').val( total );

        });
    }

    var subscriptionBack = function() {
        $('.subscription-back-btn').on('click', function(e) {
            e.preventDefault();
            var $this = $(this);

            if ( $this.data('back-to') == "details" && $('#details-nav').hasClass('active') ) {
                $tabValidation.val(1);
                $('.tab-pane').removeClass('active');
                $('#details-nav').addClass('active');
                
                $('#billing-nav').removeClass('active');
                $('#payment-nav').removeClass('active');

                $('#subscription-details-tab').addClass('active');
            }
            if ( $this.data('back-to') == "billing" && $('#billing-nav').hasClass('active') ) {
                $tabValidation.val(2);
                $('.tab-pane').removeClass('active');
                $('#details-nav').removeClass('active');
                $('#payment-nav').removeClass('active');

                $('#details-nav').addClass('active')
                $('#billing-nav').addClass('active')
                $('#billing-information-tab').addClass('active');
            }

            console.log($tabValidation.val())

        });
    };

    var subscriptionValidation = function() {
        
        var $submitPayment = $('#submit-payment-btn');
        $subscriptionForm.on('submit', function(e) {
            e.preventDefault();
            var $this = $(this);

            $submitPayment.prop('disabled', 1).val('processing payment...');

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

                $submitPayment.prop('disabled', 0).val('Submit Payment');
                if ( typeof data =="undefined" ) return;

                if ( data.is_valid && data.tab == 1 ) {
                    $tabValidation.val(2)
                    $('.tab-pane').removeClass('active');

                    $('#billing-nav').addClass('active');
                    $('#billing-information-tab').addClass('active');
                }

                if ( data.is_valid && data.tab == 2 ) {
                    $tabValidation.val(3)
                    $('.tab-pane').removeClass('active');

                    $('#payment-nav').addClass('active');
                    $('#payment-tab').addClass('active');
                }

                if ( data.is_valid && data.tab == 3 && data.charge_errors == 0 ) {
                    $tabValidation.val(3)
                    
                    tokenRequest();
                }

                if ( data.is_valid && data.tab == 3 && data.charge_errors !=0 ) {
                    bootbox.dialog({
                        title: "Error occurs!",
                        message: data.charge_errors,
                        buttons: {
                            dismiss: {
                              label: "Dismiss",
                              className: "btn-sm btn-default",
                              callback: function () {
                                $('#token').val('');
                              }
                            }
                        }
                    });
                }


                if ( data.is_valid && data.tab == 0 && data.responseCode == "APPROVED" ) {
                    // console.log("Charge Success")

                    bootbox.dialog({
                        title: "Thank you!",
                        message: "Payment Successful! You are now subscribed to Premium Politicks.",
                        buttons: {
                            dismiss: {
                              label: "Dismiss",
                              className: "btn-sm btn-default",
                              callback: function () {
                                $('#token').val('');
                                window.location = base_url + 'account';
                              }
                            }
                        }
                    });

                }






                if ( data.is_valid ) {
                    $('.form-group').removeClass('has-error').find('.help-block').text('');
                }

                if ( !data.is_valid ) {
                    var errors = '';

                    $('.form-group').removeClass('has-error');
                    $('.error-help-block').text('');
                    $.each(data.errors, function(index, val) {
                        if (val !="") {
                            errors += val + '<br/>';
                            $("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
                        }
                    });

                    bootbox.dialog({
                        title: "Error occurs!",
                        message: errors,
                        buttons: {
                            dismiss: {
                              label: "Dismiss",
                              className: "btn-sm btn-default",
                              callback: function () {
                                $("html, body").animate({ scrollTop: 0 });
                              }
                            }
                        }
                    });
                }



                console.log("complete");
            });


        });

    };


    $(function() {


        subscriptionBack();
        subscriptionValidation();
        bindEvents();

        // Pull in the public encryption key for our environment
        TCO.loadPubKey('production', function() {
            console.log("production loadPubKey")
        });

    });

});