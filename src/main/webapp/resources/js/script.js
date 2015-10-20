		$(document).ready(function(){
			getPlayer();
		});
			var cash; 
			var betAmount;
			
		function getPlayer(){
			var url = window.location.pathname;
			$.ajax({
			url: url + '/getPlayer',
			type: 'GET',
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: function(data){ 
					cash = data.cash; 
					$('#player_name').text(data.name);
					$('#cash').text(cash);
					disableButtons();
			}
			});
		}

		function addCash(){
			var url = window.location.pathname;
			var addCash = $('#addCash').val();
			var plCash = parseFloat(cash) + parseFloat(addCash);
			updateCash(plCash);
			$('#result').text("");	
			}
			
		function updateCash(value){
			var url = window.location.pathname;
			$.ajax({
			url: url + '/updateCash',
			type: 'POST',
			data: ({cash : value}),
			success: function(data){ 
					cash = data;
					$('#cash').text(cash);
					}
			});
			}	
			 
		function bet(){
			var url = window.location.pathname;
			$('#result').text("");	
			betAmount = $('#betAmount').val();
			if(parseFloat(betAmount) > parseFloat(cash)){
				$('#result').text("Not enough money!");	
			}else{$('#playerBet').text(betAmount);
				var plCash = parseFloat(cash) - parseFloat(betAmount);
				updateCash(plCash);
				$.ajax({
				url: url + '/bet',
				type: 'GET',
				dataType: "json",
				success: function(data){ 
						$('#hit').removeAttr('disabled');
						$('#stand').removeAttr('disabled');
						var playerCards = [data[0], data[2]]
						showPlayerCards(playerCards);
						var dealerCards=[data[1]];
						showDealerCards(dealerCards);
						var firstCards = $('<p>').attr({'id':'hiddenCard'});
						$('.fCard').after(firstCards); 
						$('#hiddenCard').text("****************");
						checkAfterBet();	
					}	
				
				});
			}
		}
		function hit(){
			var url = window.location.pathname;
			$.ajax({
			url: url + '/hit',
			type: 'GET',
			dataType: "json",
			success: function(data){ 
					showPlayerCards(data);
					checkAfterHit();	
					 
			}
			});
		}
		function stand(){
			var url = window.location.pathname;
			$.ajax({
			url: url + '/stand',
			type: 'GET',
			dataType: "json",
			success: function(data){ 
				showDealerCards(data);	
				checkAfterStand();	
			}		
			});
		}
		
		function getDealerCards(){
			var url = window.location.pathname;
			$.ajax({
			url: url + '/getDealerCards',
			type: 'GET',
			dataType: "json",
			success: function(data){ 
				showDealerCards(data);	
			}		
			});
		}
			
		function checkAfterBet(){
			var url = window.location.pathname;
			$.ajax({
			url: url + '/checkAfterBet',
			type: 'GET',
			dataType: "json",
			success: function(data){ 
				checkResponse(data);
			}		
			});		
		}
			
		function checkAfterHit(){
			var url = window.location.pathname;
			$.ajax({
			url: url + '/checkAfterHit',
			type: 'GET',
			dataType: "json",
			success: function(data){ 
				checkResponse(data);
			}		
			});
		}
			
		function checkAfterStand(){
			var url = window.location.pathname;
			$.ajax({
			url: url + '/checkAfterStand',
			type: 'GET',
			dataType: "json",
			success: function(data){ 
				checkResponse(data);
			}		
			});
		}
			
		function exit(){
			$.ajax({
			url: 'exit',
			type: 'GET',
			success: function(){ 
					location.href = '/BlackJack/login';
			}		
			});
		}
		
		function checkResponse(value){
			if(value == (-1)){
				} else if (value == 0){
					$('#result').text("You lose!");
					disableButtons();
				}else if (value == 1){
					getDealerCards();
					$('#result').text("Push!");
					updateCash(parseFloat(cash)+parseFloat(betAmount));
					disableButtons();
				}else if (value == 2){
					getDealerCards();
					$('#result').text("You win!");
					updateCash(parseFloat(cash)+parseFloat(betAmount)*2);
					disableButtons();
				}else {
					getDealerCards();
					$('#result').text("You win! It's BlackJack!!!");
					updateCash(parseFloat(cash)+parseFloat(betAmount)*2.5);
					disableButtons();
				}
		}
			
		function showPlayerCards(array){
			var cards = '<p>Your cards:<p>';
			for(var i = 0; i<array.length; i++){
					cards += '<p>'+array[i].rank + ' ' + array[i].suit +'</p>';	
			}				
			$('#player_cards').html(cards);			
		}
			
		function showDealerCards(array){
			var cards = '<p>Dealer cards:<p>';
			for(var i = 0; i<array.length; i++){
				cards += '<p class="fCard">'+array[i].rank + ' ' + array[i].suit +'</p>';	
			}				
			$('#dealer_cards').html(cards);	
		}
		
		function disableButtons(){
			$('#hit').attr('disabled', true);
			$('#stand').attr('disabled', true);
		}
		
		

			