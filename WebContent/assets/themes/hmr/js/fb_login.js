$(document).ready(function() {
	
	var base_url = $('#base_url').val()
	,	appId = '939422099433297'
	,	fbLogin = {
		init: function() {
			this.getSDK();
			this.loginBtn();
		},

		loginBtn: function() {
		
			$('.fb-login-auth').on('click', function(e) {
				e.preventDefault();
				// fbLogin.checkLoginState();

				FB.login(function(response) {
					console.log(response)
				  if (response.status === 'connected') {
				    fbLogin.loginClient();
				  } else if (response.status === 'not_authorized') {
				    // The person is logged into Facebook, but not your app.
				  } else {
				    // The person is not logged into Facebook, so we're not sure if
				    // they are logged into this app or not.
				  }
				}, 
				{
					scope: 'public_profile,email'
				});

			});

		},

		getSDK: function() {
			
			$.ajaxSetup({ cache: true });
			$.getScript('//connect.facebook.net/en_US/sdk.js', function(){
				FB.init({
				  appId: appId,
				  version: 'v2.5'
				});     

				$('.fb-login-auth').removeAttr('disabled');
				// FB.getLoginStatus( fbLogin.statusChangeCallback );

				FB.Event.subscribe('auth.login', fbLogin.statusChangeCallback);

			});
		},

		statusChangeCallback: function(response) {
	    	console.log('statusChangeCallback');
		    console.log(response);
		    // The response object is returned with a status field that lets the
		    // app know the current login status of the person.
		    // Full docs on the response object can be found in the documentation
		    // for FB.getLoginStatus().
		    if (response.status === 'connected') {
		      // Logged into your app and Facebook.
		      fbLogin.loginClient();
		    } else if (response.status === 'not_authorized') {
		      // The person is logged into Facebook, but not your app.
		      // $('#fb-status').text('Please log into this app.');
		    } else {
		      // The person is not logged into Facebook, so we're not sure if
		      // they are logged into this app or not.
		      // $('#fb-status').text('Please log into Facebook.');
		    }
	  },

	  checkLoginState: function() {
	  	FB.getLoginStatus(function(response) {
	      fbLogin.statusChangeCallback(response);
	    });
	  },

	  loginClient: function() {

	  	// console.log('Welcome!  Fetching your information.... ');
	    FB.api('/me?fields=first_name,last_name,email,birthday', function(response) {
	    	console.log(response)

	    	if ( typeof response.first_name !="undefined" ) {
	    		$('#customers-register-form #first_name').val( response.first_name );
	    	}
	    	if ( typeof response.last_name !="undefined" ) {
	    		$('#customers-register-form #last_name').val( response.last_name );
	    	}
	    	if ( typeof response.email !="undefined" ) {
	    		$('#customers-register-form #email').val( response.email );
	    	}
	    	if ( typeof response.birthday !="undefined" ) {
	    		$('#customers-register-form #birthday').val( response.birthday );
	    	}
	    	
			// console.log('Successful login for: ' + response.name);
			// $('#fb-status').text('Thanks for logging in, ' + response.name + '!');

			// fbLogin.register(response.email, response.id);

	    });

	  },

	  register: function(email, fb_user_id) {
	  	
	  	$.ajax({
	  		url: base_url + 'account/validate_register',
	  		type: 'POST',
	  		dataType: 'json',
	  		data: {
	  			ajax: 1,
	  			email: email,
	  			fb_user_id: fb_user_id,
	  			password: 'no-password',
	  		},
	  	})
	  	.fail(function(e) {
	  		console.log(e.responseText)
	  		console.log("error");
	  	})
	  	.always(function(data) {
	  		console.log(data)
	  		if ( typeof data !="undefined" && data.is_valid ) {
	  			window.location = base_url + 'home';
	  		}
	  		if ( typeof data !="undefined" && !data.is_valid ) {

	  			if ( data.existing_user ) {
	  				window.location = base_url + 'home';		
	  			}
	  			if ( typeof data.errors !="undefined" && typeof data.errors.email !="undefined" && !data.existing_user ) {
	  				// alert(email + ' is already registered, please login your account.');
	  				// window.location = base_url + 'account/login';
	  				
	  				$('.form-group').removeClass('has-error');
                    $('.error-help-block').text('');
                    $.each(data.errors, function(index, val) {
                        if (val !="") {
                            $("#" + index).parents('.form-group').addClass('has-error').find('.help-block').text(val).addClass('error-help-block');
                        }
                    });

	  				
	  			}

	  		}
	  		console.log("complete");
	  	});
	  	

	  }

	};


	fbLogin.init();

});