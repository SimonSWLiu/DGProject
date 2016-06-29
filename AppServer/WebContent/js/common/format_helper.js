//
function format_helper() {

}

format_helper.prototype.formatPrice = function(price) {

	if (!isNaN(price)) {

		if (price == 0) {
			
			return 0;
		} else {
			
			return Number(price).toFixed(2);
		}
	} else {
		
		return price;
	}
}
