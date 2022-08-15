<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../../admin/includes/hd.jsp"></jsp:include>
<jsp:include page="../../admin/includes/header.jsp"></jsp:include>
<jsp:include page="../../admin/includes/sidebar1.jsp"></jsp:include>
<jsp:include page="../../admin/includes/container.jsp"></jsp:include>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<style>
    .col {
        display: flex;
        align-items: center;
    }
</style>

<div class="content flex-column-fluid" id="kt_content">
	
	<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
		<!--begin::Card header-->
		<div class="card-header cursor-pointer">
			<!--begin::Card title-->
			<div class="card-title m-0">
				<h3 class="fw-bold m-0">Đổi mật khẩu</h3>
			</div>
			<!--end::Card title-->
			<!--begin::Action-->
			<!--end::Action-->
		</div>
		<!--begin::Card header-->
		<!--begin::Card body-->
		<div class="card-body p-9 ">
			<!--begin::Input group-->
			<form class="wrapper p-0 px-20 d-flex flex-column align-items-center"
				  method="POST" action="${contextPath}/staff/account/changePassword">
				<h4 class="row w-75 row-cols-2 mb-10 h-40px">
					<div class="col">Mật khẩu cũ</div>
					<input type="password"
						   class="form-control w-300px h-40px oldPassword"
						   name="oldPassword"
					/>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10 h-40px">
					<div class="col">Mật khẩu mới</div>
					<input type="password"
						   class="form-control w-300px h-40px newPassword"
						   name="password"
					/>
				</h4>
				<div class="my-5">
					<button type="submit" class="btn btn-primary">Xác nhận</button>
					<a href="${contextPath}/staff/info" class="btn btn-warning mx-5">
						Hủy
					</a>
				</div>
			</form>
			<!--end::Input group-->
			
			<!--begin::Input group-->
			<!--end::Input group-->
			
			<!--begin::Input group-->
			<!--end::Input group-->
			
			<!--begin::Input group-->
			<!--end::Input group-->
			
			<!--begin::Input group-->
			<!--end::Input group-->
			
			<!--begin::Input group-->
			<!--end::Input group-->
		</div>
		<!--end::Card body-->
	</div>
</div>
<jsp:include page="../../admin/includes/footer.jsp"></jsp:include>
<jsp:include page="../../admin/includes/end.jsp"></jsp:include>

<%--<script>--%>
<%--	document.query--%>
<%--</script>--%>

