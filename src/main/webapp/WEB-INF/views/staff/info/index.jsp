<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<h3 class="fw-bold m-0">Thông tin cá nhân</h3>
			</div>
			<!--end::Card title-->
			<!--begin::Action-->
			<a href="${contextPath}/staff/info/edit"
			   class="btn btn-primary align-self-center edit-profile-button"
			>
				Chỉnh sửa
			</a>
			<!--end::Action-->
		</div>
		<!--begin::Card header-->
		<!--begin::Card body-->
		<div class="card-body p-9 ">
			<!--begin::Input group-->
			<div class="wrapper p-0 px-20 d-flex flex-column align-items-center">
				<h4 class="row w-75 row-cols-2 mb-10">
					<div class="col">Mã nhân viên</div>
					<div class="col w-300px">${employee.id}</div>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10">
					<div class="col">Tên nhân viên</div>
					<div class="col w-300px">${employee.name}</div>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10">
					<div class="col">Địa chỉ</div>
					<div class="col w-300px">${employee.address}</div>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10">
					<div class="col">Email</div>
					<div class="col w-300px">${employee.email}</div>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10">
					<div class="col">Số điện thoại</div>
					<div class="col w-300px">${employee.phoneNumber}</div>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10">
					<div class="col">Giới tính</div>
					<div class="col w-300px">${employee.gender}</div>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10">
					<div class="col">Ngày sinh</div>
					<div class="col w-300px"><fmt:formatDate pattern="dd-MM-yyyy"
															 value="${employee.birthDay}"/></div>
				</h4>
				<h4 class="row w-75 row-cols-2 mb-10">
					<div class="col">Trạng thái hôn nhân</div>
					<div class="col w-300px">
						<c:choose>
							<c:when test="${employee.maritalStatus eq 'Đã kết hôn'}">Đã kết hôn</c:when>
							<c:otherwise>Độc thân</c:otherwise>
						</c:choose>
					</div>
				</h4>
			</div>
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

