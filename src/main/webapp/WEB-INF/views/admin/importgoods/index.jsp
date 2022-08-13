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
                <h3 class="fw-bold m-0">Quản lý nhập hàng</h3>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <span class="btn btn-primary add-new-importgood align-self-center">Thêm nguyên liệu</span>
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">

            <!--end::Input group-->
            <div class="table-responsive">
                <table class="table table-row-bordered gy-5">
                    <thead>
                    <tr class="fw-bold fs-6 text-gray-800">
                        <%--                        <th class="table-sort-desc">Mã loại bàn</th>--%>
                        <th>ID</th>
                        <th>Nhân viên nhập hàng</th>
                        <th>Thời gian nhập</th>
                        <th>Hành động</th>
                        <th>&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${importgoods}">
                        <tr>
                            <th scope="row">${  item.id }</th>
                            <td>${  item.employee.id }</td>
                            <td>${  item.dateFormat }</td>
                            <td>
                                <a href="" class="btn btn-warning mx-1">Xem</a>
                                <a href="" class="btn btn-success mx-1">Sửa</a>
                                <a href="" class="btn btn-danger mx-1">Xoá</a>
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
<script !src="">
    $('.add-new-importgood').click(function () {
        $.ajax({
            url: '${pageContext.request.contextPath}/admin/importgoods',
            type: 'POST',
            data: JSON.stringify({}),
            contentType: 'application/json',
            success: function (result) {
               if(result.check == true){
                   toastr.success("Thêm nguyên liệu thành công");
                   setTimeout(function () {
                       window.location.href = '${pageContext.request.contextPath}/admin/importgoods/'+result.value.idImportGoods;
                   }, 1000);
               }
               else {
                   toastr.error("Thêm nguyên liệu thất bại");
               }
            }
        });
    });
</script>
