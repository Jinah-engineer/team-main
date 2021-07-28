<%@ tag language="java" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="pinfo" scope="request"/>
</sec:authorize>
	



<nav class="Tags"> 
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=53f2oltjp5"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=53f2oltjp5&submodules=geocoder"></script>

	<div id="map" style="width: 50%; height: 400px;"></div>
<script type="text/javascript">
$(function() {
	var loadUserLat = $("#userlat").val();
	var loadUserLag = $("#userlag").val();
	
	/* ���� */
	var map = new naver.maps.Map('map', {
		center : new naver.maps.LatLng(loadUserLag, loadUserLat),
		zoom : 16
	});
	/* ���� ��Ŀ */
	var markerOptions = {
			position: new naver.maps.LatLng(loadUserLag, loadUserLat),
		    map: map,
		    icon: {		    	
		        content: [	
			        		`<div onmouseover="$('#ad1').show();" onmouseout="$('#ad1').hide();" >` +
				        		`<div><img src="${imgRoot}icon/blue_re-pict-house-base.png_32.png"></div>`+
				        		`<div id="ad1" style="padding-top:5px; padding-bottom:5px; padding-left:5px; padding-right:5px;
				        		background-color:#88C9F2; color:white; text-align:center; border:1px;
				        		border-radius:14px; opacity:75%; display:none;">����:4.0 <br> �ּ�:${pinfo.member.userAddress}</div>`+
			        		`</div>`	        	
		        	].join(''),
		    },
		};
		var marker = new naver.maps.Marker(markerOptions);
		
		var data = {
				storelat : loadUserLat,
				storelag : loadUserLag
		}
		
		$.ajax({
			type: 'POST',
			url: '${appRoot}/won/tagGetB2bStoreInfo',
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(list) {
				
				/* ���ֺ� ������ ��Ŀ */
				for(let address of list ){
					
				let laundrymarker = {
						position: new naver.maps.LatLng(address.lag, address.lat),
					    map: map,
					    icon: {
					        content: [	
						        		`<div onmouseover="$(this).find('.ad2').show();" onmouseout="$(this).find('.ad2').hide();">` +
							        		`<div><img src="${imgRoot}icon/blue_re-pict-house-base.png_32.png"></div>`+
							        		`<div class="ad2"  style="padding-top:5px; padding-bottom:5px; padding-left:5px; padding-right:5px;
													        		  background-color:#88C9F2; color:white; text-align:center; border:1px;
													        	  	  border-radius:14px; opacity:75%; display:none">����:4.0<br> �ּ�: \${address.address} </div>`+                   
						        		`</div>`
									 ].join('')
					        	
					       /*  size: new naver.maps.Size(22, 35),
					        anchor: new naver.maps.Point(15, 35)  */
					    },
					    animation: naver.maps.Animation.BOUNCE
					};
					 let marker = new naver.maps.Marker(laundrymarker);
					}
				},
				error : function() {
					console.log("����");
				}
			})
			
		})

</script>

<input type="text" id="userlat" value="${pinfo.member.lat }">
<input type="text" id="userlag" value="${pinfo.member.lag }">
</nav>

