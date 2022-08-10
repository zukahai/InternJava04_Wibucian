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
				<h3 class="fw-bold m-0">Chỉnh sửa thông tin cá nhân</h3>
			</div>
			<!--end::Card title-->
			<!--begin::Action-->
			<!--end::Action-->
		</div>
		<!--begin::Card header-->
		<!--begin::Card body-->
		<div class="card-body p-9 ">
			<!--begin::Input group-->
			<form class="wrapper p-0 px-20 d-flex flex-column align-items-center">
				<h4 class="row w-75 row-cols-2 mb-10 h-40px">
					<div class="col">Mã nhân viên</div>
					<div class="col w-300px">${employee.id}</div>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10 h-40px">
					<div class="col">Tên nhân viên</div>
					<input type="text"
						   class="form-control w-300px h-40px name"
						   value="${employee.name}"
						   placeholder="Tên nhân viên"
						   name="name"
					/>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10 h-40px">
					<div class="col">Địa chỉ</div>
					<input type="text"
						   class="form-control w-300px h-40px address"
						   value="${employee.address}"
						   placeholder="Địa chỉ"
						   name="address"
					/>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10 h-40px">
					<div class="col">Email</div>
					<div class="col w-300px">${employee.email}</div>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10 h-40px">
					<div class="col">Số điện thoại</div>
					<input type="text"
						   class="form-control w-300px h-40px phoneNumber"
						   value="${employee.phoneNumber}"
						   placeholder="Số điện thoại"
						   name="phoneNumber"
					/>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10 h-40px">
					<div class="col">Giới tính</div>
					<div class="col w-300px">${employee.gender}</div>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10 h-40px">
					<div class="col">Ngày sinh</div>
					<input type="date"
						   class="form-control w-250px h-40px maritalStatus"
						   value="${employee.birthDay}"
						   name="birthDay"
					/>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10 h-40px">
					<div class="col">Trạng thái hôn nhân</div>
					<div class="form-check form-check-custom form-check-solid justify-content-between">
						<div class="d-flex align-items-center">
							<input class="form-check-input mx-3" type="radio"
								   name="maritalStatus" value="1"/>
							<span>Độc thân</span>
						</div>
						<div class="d-flex align-items-center">
							<input class="form-check-input mx-3" type="radio" value="2"
								   name="maritalStatus"/>
							<span>Đã kết hôn</span>
						</div>
					</div>
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

