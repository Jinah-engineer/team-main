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
	let userAddress = "${pinfo.member.address}";
	
	naver.maps.Service
	.geocode({ query : userAddress
			// String Ÿ���� �ּҰ�
			},
			function(status, response) {
				if (status !== naver.maps.Service.Status.OK) {
					// ������ ���� ���� ��� 
					return alert('Something wrong!');

				}

				var result = response.v2, items = result.addresses;
				let lat = parseFloat(items[0].x);
				let lag = parseFloat(items[0].y);

				console.log(lat);
				console.log(lag);
				
	
	/* ���� */
	var map = new naver.maps.Map('map', {
		center : new naver.maps.LatLng(lag, lat),
		zoom : 16
	});
	/* ���� ��Ŀ */
	var markerOptions = {
			position: new naver.maps.LatLng(lag, lat),
		    map: map,
		    icon: {		    	
		        content: [	
			        		`<div onmouseover="$('#ad1').show();" onmouseout="$('#ad1').hide();" >` +
				        		`<div><img src="${appRoot}/resources/image/home_button.png"></div>`+
				        		`<div id="ad1" style="font-size:8px;padding-top:5px; padding-bottom:5px; padding-left:5px; padding-right:5px;
				        		background-color:#88C9F2; color:black; text-align:center; border:1px;
				        		border-radius:14px; opacity:75%; display:none;">����:4.0 <br> �ּ�:${pinfo.member.userAddress}</div>`+
			        		`</div>`	        	
		        	].join(''),
		    },
		};
		var marker = new naver.maps.Marker(markerOptions);
		
		var data = {
				storelat : lat,
				storelag : lag
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
						position: new naver.maps.LatLng(address.storelag, address.storelat),
					    map: map,
					    icon: {
					        content: [	
						        		`<div onmouseover="$(this).find('.ad2').show();" onmouseout="$(this).find('.ad2').hide();">` +
							        		`<div><img src="${appRoot}/resources/image/home_button.png"></div>`+
							        		`<div class="ad2"  style="font-size:8px; padding-top:5px; padding-bottom:5px; padding-left:5px; padding-right:5px;
													        		  background-color:#88C9F2; color:black; text-align:center; border:1px;
													        	  	  border-radius:14px; opacity:75%; display:none">����:4.0<br> �ּ�: \${address.storeaddress} </div>`+                   
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
		})

</script>
</nav>

