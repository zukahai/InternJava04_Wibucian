<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<base href="/">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>
<div class="content flex-column-fluid" id="kt_content">

    <div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
        <!--begin::Card header-->
        <div class="card-header cursor-pointer">
            <!--begin::Card title-->
            <div class="card-title m-0">
                <h3 class="fw-bold m-0">Danh sách nhân viên</h3>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <a
                    href="admin/employee/create"
                    class="btn btn-primary align-self-center"
            >Thêm Nhân Viên</a
            >
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">
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

            <!--begin::Input group-->
            <!--end::Input group-->
            <div class="table-responsive">
                <table class="table table-row-bordered gy-5">
                    <thead>
                    <tr class="fw-bold fs-6 text-gray-800">
                        <th class ="text-center">Mã Nhân Viên</th>
                        <th class ="text-center">Hình Ảnh</th>
                        <th class ="text-center">Họ & Tên</th>
                        <th class ="text-center ">SĐT</th>
                        <th class ="text-center">Hệ Số Lương</th>
                        <th class ="text-center" >Chức Năng</th>
                        <th>Mã Nhân Viên</th>
                        <th>Hình Ảnh</th>
                        <th>Họ & Tên</th>
                        <th>SĐT</th>
                        <th>Hệ Số Lương</th>
                        <th class ="justify-content-center d-flex" >Chức Năng</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${DanhSach}">
                    <tr>
                        <th scope="row">${item.id}</th>
                        <td><div class ="symbol symbol-circle symbol-50px overflow-hidden me-3">
                            <img class="w-100" src="${pageContext.request.contextPath}/storage/file/img/avatar.jpg">
                        </div></td>
                        <td>${ item.name}</td>
                        <td>${ item.phoneNumber}</td>
                        <td>${ item.coefficientsSalary}</td>
                        <td>
                            <a href="" class="badge badge-warning mx-1">Hoạt động</a>
                        </td>
                        <td>
                            <a href="" class="btn btn-warning mx-1">Sửa</a>
                            <a href="" class="btn btn-success mx-1">Xem</a>
                            <a href="" class="btn btn-danger mx-1">Xóa</a>
                        </td>
                    </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!--end::Card body-->
    </div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>

