
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>
<div class="content flex-column-fluid" id="kt_content">

    <div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
        <div class="card-header cursor-pointer">
            <div class="card-title m-0">
                <h3 class="fw-bold m-0">Thêm Nhân Viên</h3>
            </div>
            <a href="#" class="btn btn-primary align-self-center">Danh sách nhân viên</a
            >
        </div>
        <div class="card-body p-9">
            <form action="/admin/employee" method="post">

                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="employeeName" name="employeeName" placeholder="VIP"/>
                    <label for="employeeName">Tên nhân viên mới</label>
                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="employeeAddress" name="employeeAddress" placeholder="VIP"/>
                    <label for="employeeAddress">Địa chỉ</label>
                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="employeeEmail" name="employeeEmail" placeholder="VIP"/>
                    <label for="employeeEmail">Email</label>
                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="employeePhone" name="employeePhone" placeholder="VIP"/>
                    <label for="employeePhone">Phone</label>
                </div>
                <div class="form-floating my-5">
                    <input type="date" class="form-control" id="employeeBirthday" name="employeeBirthday" placeholder="VIP"/>
                    <label for="employeePhone">Ngày sinh</label>
                </div>
                <div class="form-floating">
                    <select class="form-select h-100" id="employeeGender" aria-label="Floating label select example">
                        <option selected>Bấm để chọn</option>
                        <option value="1">Nam</option>
                        <option value="2">Nữ</option>
                        <option value="3">Khác</option>
                    </select>
                    <label for="employeeGender">Giới tính</label>
                </div> <br>
                <div class="form-floating">
                    <select class="form-select h-100" id="employeeStatus" aria-label="Floating label select example">
                        <option selected>Bấm để chọn</option>
                        <option value="1">Độc thân</option>
                        <option value="2">Đã kết hôn</option>
                    </select>
                    <label for="employeeStatus">Tình trạng hôn nhân</label>
                </div>
                <div class="form-floating my-5">
                    <input value="0" type="number" class="form-control" id="employeeSalary" name="employeeSalary" placeholder="VIP"/>
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
