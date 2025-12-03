$().ready(function() {
		
	const onlyDigits = /[0-9]/;
	const allowKeys = new Set([
	  'Backspace','Delete','ArrowLeft','ArrowRight','Home','End','Tab'
	]);

	const buyDays = $('#buyDays');

	function sanitize(){
	  buyDays.val((buyDays.val() || '').replace(/\D+/g, '')); 
	}

	buyDays.on('keydown', function(e){
	  if (allowKeys.has(e.key)) {
		return;
	}
	  if (!onlyDigits.test(e.key)) {
	    e.preventDefault();
	  }
	});
	
	buyDays.on('input', sanitize);
	
	
///////
	const start 	= $('#startDate');
	const days  	= $('#buyDays');
	const end       = $('#endDate');
	const cmpnStart = $('#campStartDate');
	const campEnd   = $('#pstEndDt');
	// Date 형태 생성
	function parseDate(yyyyMMdd) {
		if (!yyyyMMdd) {
			return null
		};
		const [y, m, d] = yyyyMMdd.split('-').map(Number);
		if (!y || !m || !d){
			return null;
		} 
		return new Date(y, m - 1, d);
	}
	
	// Date → YYYY-MM-DD
	function formatDate(dt) {
	  const y = dt.getFullYear();
	  const m = String(dt.getMonth() + 1).padStart(2, '0');
	  const d = String(dt.getDate()).padStart(2, '0');
	  return `${y}-${m}-${d}`;
	}
	
	// dt + n일
	function addDays(dt, n) {
	  const copy = new Date(dt);
	  copy.setDate(copy.getDate() + n);
	  return copy;
	}
	
	function recalc() {
	  const startVal = start.val();
	  const daysStr  = (days.val() || '').trim();
	  const startDate = parseDate(startVal);
	  const daysNum  = Number(daysStr);
	  
	  if (!startDate || !daysStr || !Number.isInteger(daysNum) || daysNum <= 0) {
	    end.val('');
		campEnd.attr('min','');
        $('#totalPayHidden').val('');
	    return;
	  }
	  const endDate = addDays(startDate, daysNum);
	  const cmpnStartDate = addDays(endDate, 3);
	  end.val(formatDate(endDate));
	  cmpnStart.val(formatDate(cmpnStartDate));	
	  const campStartStr = formatDate(cmpnStartDate);
	  campEnd.attr('min', formatDate(addDays(cmpnStartDate,7)));
      
     
	}
	
	start.on('change input', recalc);
	days.on('input', function(){
	  recalc();
	});
	
	$(document).on('input', '#buyDays', function () {
	  $('#payDay').text($(this).val());
	  totalPay = ($(this).val()*$('.payPrice').data('dayprice'))
	  				+ ($('.payPrice').data('rcrtprsnn')*Number($('.payPerson').data('person'))); 
	  $('.total-pay').text(totalPay);
      $('#totalPayHidden').val(totalPay);
	});
	
    campEnd.on('blur', function(){
      const start = parseDate(cmpnStart.val());
      const end   = parseDate($(this).val());
      if(!start || !end) return;

      const minEnd = addDays(start, 6); // 최소 7일 구간
      if(end < minEnd){
        $(this).val(formatDate(minEnd)); // 자동 보정
      }
    });
    
    
    $(document).on('click', '.payment-bottom-button', function (e) {
      e.preventDefault();
      const cmpnId = $('#campaign-payment-block').data('cmpn-id'); 
      if (!cmpnId) {
        alert('캠페인 ID를 찾을 수 없습니다.');
        return;
      }
      $.ajax({
        url: `/adv/pay/campaign/${encodeURIComponent(cmpnId)}`,
        method: 'POST',
        data: $('#payForm').serialize(),
        success: function(res) { 
            location.href = "/adv/pay/"+cmpnId ; 
        }
      })
    });
   
    
    const tomorrow = new Date(Date.now() + 24 * 60 * 60 * 1000);
    const y = tomorrow.getFullYear();
    const m = String(tomorrow.getMonth() + 1).padStart(2, '0');
    const d = String(tomorrow.getDate()).padStart(2, '0');
    start.attr('min', `${y}-${m}-${d}`);
        
    start.on('blur', function(){
      const start = parseDate(cmpnStart.val());
      const today   = tomorrow;
      if(start < today){
        $(this).val(formatDate(today)); // 자동 보정
      }
    });    
    
    
	// 초기 계산(페이지에 값이 미리 채워져 있을 경우 대비)
	recalc();
	

	
	
});