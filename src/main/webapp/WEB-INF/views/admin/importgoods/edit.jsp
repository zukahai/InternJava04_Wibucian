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
                <h3 class="fw-bold m-0">Sửa Nhập Hàng</h3>
            </div>
            <a href="admin/importgoods/" class="btn btn-primary align-self-center">Danh sách nhập hàng</a
            >
        </div>
        <div class="card-body p-9">
            <form action="/admin/importgoods/edit" method="post">
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="importgoodsId" name="importgoodsId" value="${nhaphang.id}"readonly/>
                    <label for="importgoodsId">ID</label>
                </div>
                <div class="form-floating my-5">
                    <input type="text" class="form-control" id="idEmployee" name="idEmployee" value="${nhaphang.employee.id}"readonly/>
                    <label for="idEmployee">Người nhập hàng</label>
                </div>
                <div class="form-floating my-5">
                    <input type="date" class="form-control" id="timeImport" name="timeImport" value="${nhaphang.timeImport}"/>
                    <label for="timeImport">Thời gian nhập</label>
                </div>


                <div class="text-center my-5">
                    <button class="btn btn-primary" type="submit">Sửa</button>
                </div>
            </form>
        </div>
        <!--end::Card body-->
    </div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>