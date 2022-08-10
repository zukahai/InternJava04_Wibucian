<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar2.jsp"></jsp:include>
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
            <form action="/admin/employee/update/${employee.id}" method="post" enctype="multipart/form-data">
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
                                        <div class="fv-row mb-7">
                                            <label class="d-block fw-semibold fs-6 mb-5">Avatar</label>
                                            <style>.image-input-placeholder {
                                                background-image: url("/admin/assets/file-upload/${employee.srcEmployee}");
                                            }

                                            [data-theme="dark"] .image-input-placeholder {
                                                background-image: url("/admin/assets/file-upload/${employee.srcEmployee}");
                                            }</style>
                                            <div class="image-input image-input-outline image-input-placeholder" data-kt-image-input="true">
                                                <div class="image-input-wrapper w-150px h-150px"
                                                     style="background-image: url(/admin/assets/file-upload/${employee.srcEmployee}");"></div>
                                                <label class="btn btn-icon btn-circle btn-active-color-primary w-25px h-25px bg-body shadow"
                                                       data-kt-image-input-action="change" data-bs-toggle="tooltip" title="Change avatar">
                                                    <i class="bi bi-pencil-fill fs-7"></i>
                                                    <input type="file"  name="avatar" accept=".png, .jpg, .jpeg"/>
                                                    <input type="hidden" name="srcEmployee"/>
                                                </label>
                                                <span class="btn btn-icon btn-circle btn-active-color-primary w-25px h-25px bg-body shadow"
                                                      data-kt-image-input-action="cancel" data-bs-toggle="tooltip" title="Cancel avatar">
																				<i class="bi bi-x fs-2"></i>
																			</span>
                                                <i class="bi bi-x fs-2"></i>
                                                </span>
                                            </div>
                                            <div class="form-text">Allowed file types: png, jpg, jpeg.</div>
                                            <!--end::Hint-->
                                        </div>
                                        <!--end::Avatar-->
                                        <!--begin::Name-->
                                    <a class="fs-3 text-gray-800 text-hover-primary fw-bold mb-3">${employee.id}</a>
                                    <div class="mb-15">
                                        <!--begin::Badge-->
                                        <div class="badge badge-lg badge-light-primary d-inline">Nhân viên</div>
                                        <!--begin::Badge-->
                                    </div>
                                        <!--<label for="employeeName">${employee.name}</label>-->
                                        <!--end::Name-->
                                        <!--begin::Position-->
                                        <div class="mb-9">
                                            <!--begin::Badge-->

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
                                        <td>Tên nhân viên</td>
                                        <td>
                                                <input type="text"  class="form-control" id="employeeName" name="name" placeholder="VIP" value="${employee.name}"/>
                                                <!--<label for="name">Tên loại bàn</label>-->
                                        </td>

                                    </tr>
                                    <tr>
                                        <td>Địa Chỉ</td>
                                        <td><input type="text" class="form-control" id="employeeAddress" name="address" value="${employee.address}"/>

                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td><input type="text" class="form-control" id="employeeEmail" name="email" value="${employee.email}"/></td>

                                    </tr>
                                    <tr>
                                        <td>Giới Tính</td>
                                        <td> <select class="form-select h-100" id="employeeGender" name="gender" aria-label="Floating label select example" value="${employee.gender}" >
                                            <option value="Nam">Nam</option>
                                            <option value="Nữ">Nữ</option>
                                            <option value="Khác">Khác</option>
                                        </select></td>
                                    </tr>
                                    <tr>
                                        <td>Ngày sinh</td>
                                        <td><input type="date" class="form-control" id="employeeBirthday" name="birthDay" value="${employee.birthDay}"/></td>

                                    </tr>
                                    <tr>
                                        <td>Tình trạng</td>
                                        <td><select class="form-select h-100" id="employeeStatus" name="maritalStatus" aria-label="Floating label select example" value="${employee.maritalStatus}">

                                            <option value="Độc thân">Độc thân</option>
                                            <option value="Đã kết hôn">Đã kết hôn</option>
                                        </select></td>
                                    </tr>
                                    <tr>
                                        <td>Số điện thoại</td>
                                        <td><input type="text" class="form-control" id="employeePhone" name="phoneNumber" value="${employee.phoneNumber}"/></td>
                                    </tr>
                                    <tr>
                                        <td>Hệ số lương</td>
                                        <td><input value="0" type="number" class="form-control" id="employeeSalary" name="coefficientsSalary" value="${employee.coefficientsSalary}"/>
                                        </td>
                                    </tr>
                                    </tbody>
                                    <!--end::Table body-->
                                </table>
                                <!--end::Table-->
                            </div>
                            <!--end::Card body-->
                        </div></div>

                </div>
                <div class="text-center my-5">
                    <button class="btn btn-primary" type="submit">Cập nhật</button>
                </div>
            </form>
            </div>
            <jsp:include page="../includes/footer.jsp"></jsp:include>
            <jsp:include page="../includes/end.jsp"></jsp:include>
