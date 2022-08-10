<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <h3 class="fw-bold m-0">Dach sách nhân viên</h3>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <a href="admin/employee/" class="btn btn-primary align-self-center">Danh sách nhân viên</a
            >
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="row mt-10">
            <div class="col">
                <div class="content flex-column-fluid" id="">
                    <!--begin::Layout-->
                    <div class="d-flex flex-column flex-lg-row">
                        <!--begin::Sidebar-->
                        <div class="flex-column flex-lg-row-auto w-lg-250px w-xl-350px mb-10">
                            <!--begin::Card-->
                            <div class="card mb-5 mb-xl-8">
                                <!--begin::Card body-->
                                <div class="card-body">
                                    <!--begin::Summary-->
                                    <!--begin::User Info-->
                                    <div class="d-flex flex-center flex-column py-5">
                                        <!--begin::Avatar-->
                                        <div class="symbol symbol-200px symbol-circle mb-7">
                                            <img src="/admin/assets/file-upload/${employee.srcEmployee}" alt="image"/>
                                        </div>
                                        <!--end::Avatar-->
                                        <!--begin::Name-->
                                        <a class="fs-3 text-gray-800 text-hover-primary fw-bold mb-3">${employee.name}</a>
                                        <!--end::Name-->
                                        <!--begin::Position-->
                                        <div class="mb-9">
                                            <!--begin::Badge-->
                                            <div class="badge badge-lg badge-light-primary d-inline">Nhân viên</div>
                                            <!--begin::Badge-->
                                        </div>
                                        <!--end::Position-->
                                        <!--begin::Info-->
                                        <!--begin::Info heading-->
                                    </div>
                                </div>
                            </div>
            </div>
            <div class="col">
                <div class="table-responsive">
                    <!--begin::Table-->
                    <table class="table align-middle table-row-dashed gy-5" id="kt_table_users_login_session">
                        <!--begin::Table body-->
                        <tbody class="fs-6 fw-semibold text-gray-600">
                        <tr>
                            <td>Mã nhân viên</td>
                            <td>${employee.id}</td>

                        </tr>
                        <tr>
                            <td>Địa Chỉ</td>
                            <td>${employee.address}</td>
                        </tr>
                        <tr>
                            <td>Email</td>
                            <td>${employee.email}</td>
                        </tr>
                        <tr>
                            <td>Giới Tính</td>
                            <td>${employee.gender}</td>
                        </tr>
                        <tr>
                            <td>Ngày sinh</td>
                            <td>${employee.birthDay}</td>
                        </tr>
                        <tr>
                            <td>Tình trạng</td>
                            <td>${employee.maritalStatus}</td>
                        </tr>
                        <tr>
                            <td>Số điện thoại</td>
                            <td>${employee.phoneNumber}</td>
                        </tr>
                        <tr>
                            <td>Hệ số lương</td>
                            <td>${employee.coefficientsSalary}</td>
                        </tr>
                        </tbody>
                        <!--end::Table body-->
                    </table>
                    <!--end::Table-->
                </div>
                <!--end::Card body-->
            </div></div>


        </div>


    <jsp:include page="../includes/footer.jsp"></jsp:include>
    <jsp:include page="../includes/end.jsp"></jsp:include>
