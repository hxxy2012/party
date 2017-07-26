	$(document).ready(function(){
		var defaultOpts = { interval: 5000, fadeInTime: 300, fadeOutTime: 200 };
		var _titles = $(".slide-controls span");
//		var _smallImage = $(".smalltu li");
		var _bodies = $(".slide-banner li");
		var _count = _titles.length;
		var _current = 0;
		var _intervalID = null;
		var stop = function() { window.clearInterval(_intervalID); };
		var slide = function(opts) {
			if (opts) {
				_current = opts.current || 0;
			} else {
				_current = (_current >= (_count - 1)) ? 0 : (++_current);
			};
			_bodies.filter(":visible").fadeOut(defaultOpts.fadeOutTime, function() {
				_bodies.eq(_current).fadeIn(defaultOpts.fadeInTime);
				_bodies.removeClass("curr").eq(_current).addClass("curr");
			});
			_titles.removeClass("curr").eq(_current).addClass("curr");
			/*_smallImage.removeClass("borderSty").eq(_current).addClass("borderSty");*/
		}; 
		var slidego = function() {
			stop();
			_intervalID = window.setInterval(function() { slide(); }, defaultOpts.interval);
		}; 
		var itemMouseOver = function(target, items) {
			stop();
			var i = $.inArray(target, items);
			slide({ current: i });
		}; 
		_titles.hover(
				function() { 
							if($(this).attr('class')!='curr'){
								itemMouseOver(this, _titles); 
							}
							else{
								stop();
							}
						}, slidego);
//		_smallImage.hover(function() { if($(this).attr('class')!='curr'){itemMouseOver(this, _smallImage); }else{stop();}}, slidego);
		_bodies.hover(stop, slidego);
		slidego();
	});

