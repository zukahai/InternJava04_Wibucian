<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <base href="/">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <jsp:include page="../includes/hd.jsp"></jsp:include>
        <jsp:include page="../includes/header.jsp"></jsp:include>
        <jsp:include page="../includes/sidebar2.jsp"></jsp:include>
        <jsp:include page="../includes/container.jsp"></jsp:include>
        <div class="content flex-column-fluid" id="kt_content">

            <div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
                <div class="card-header cursor-pointer">
                    <div class="card-title m-0">
                        <h3 class="fw-bold m-0">Thêm Nhân Viên</h3>
                    </div>
                    <a href="admin/employee/" class="btn btn-primary align-self-center">Danh sách nhân viên</a
            >
        </div>
        <div class="card-body p-9">
            <form action="/admin/employee" method="post">
                <div class="rounded border p-5">
                        <div class="fv-row mb-7">
                            <label class="d-block fw-semibold fs-6 mb-5">Avatar</label>
                            <style>.image-input-placeholder { background-image: url(/admin/assets/img/avatar.jpg); } [data-theme="dark"] .image-input-placeholder { background-image: url(/admin/assets/img/avatar.jpg); }</style>
                            <div class="image-input image-input-outline image-input-placeholder" data-kt-image-input="true">
                                <div class="image-input-wrapper w-150px h-150px" style="background-image: url(/admin/assets/img/avatar.jpg);"></div>
                                <label class="btn btn-icon btn-circle btn-active-color-primary w-25px h-25px bg-body shadow" data-kt-image-input-action="change" data-bs-toggle="tooltip" title="Change avatar">
                                    <i class="bi bi-pencil-fill fs-7"></i>
                                    <input type="file" name="srcEmployee" accept=".png, .jpg, .jpeg" />
                                    <input type="hidden" name="srcEmployee" />
                                </label>
                                <span class="btn btn-icon btn-circle btn-active-color-primary w-25px h-25px bg-body shadow" data-kt-image-input-action="cancel" data-bs-toggle="tooltip" title="Cancel avatar">
																				<i class="bi bi-x fs-2"></i>
																			</span>
                                <i class="bi bi-x fs-2"></i>
                                </span>
                            </div>
                            <div class="form-text">Allowed file types: png, jpg, jpeg.</div>
                            <!--end::Hint-->
                        </div>

                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="employeeName" name="name" placeholder="VIP"/>
                    <label for="employeeName">Tên nhân viên mới</label>
                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="employeeAddress" name="address" placeholder="VIP"/>
                    <label for="employeeAddress">Địa chỉ</label>
                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="employeeEmail" name="email" placeholder="VIP"/>
                    <label for="employeeEmail">Email</label>
                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="employeePhone" name="phoneNumber" placeholder="VIP"/>
                    <label for="employeePhone">Phone</label>
                </div>
                <div class="form-floating my-5">
                    <input type="date" class="form-control" id="employeeBirthday" name="birthDay" placeholder="VIP"/>
                    <label for="employeePhone">Ngày sinh</label>
                </div>
                <div class="form-floating">
                    <select class="form-select h-100" id="employeeGender" name="gender" aria-label="Floating label select example">
                        <option selected>Bấm để chọn</option>
                        <option value="Nam">Nam</option>
                        <option value="Nữ">Nữ</option>
                        <option value="Khác">Khác</option>
                    </select>
                    <label for="employeeGender">Giới tính</label>
                </div> <br>
                <div class="form-floating">
                    <select class="form-select h-100" id="employeeStatus" name="maritalStatus" aria-label="Floating label select example">
                        <option selected>Bấm để chọn</option>
                        <option value="Độc thân">Độc thân</option>
                        <option value="Đã kết hôn">Đã kết hôn</option>
                    </select>
                    <label for="employeeStatus">Tình trạng hôn nhân</label>
                </div>
                <div class="form-floating my-5">
                    <input value="0" type="number" class="form-control" id="employeeSalary" name="coefficientsSalary" placeholder="VIP"/>
                    <label for="employeeSalary">Hệ số lương</label>
                </div>
                <div class="text-center my-5">
                    <button class="btn btn-primary" type="submit">Thêm</button>
                </div>
            </form>
        </div>
        <!--end::Card body-->
    </div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>