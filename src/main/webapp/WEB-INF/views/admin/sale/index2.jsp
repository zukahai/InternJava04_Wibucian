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
                <h3 class="fw-bold m-0">Danh sách sản phẩm</h3>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <a
                    href="admin/sale/create"
                    class="btn btn-primary align-self-center"
            >Thêm sản phẩm</a
            >
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
                        <th>Mã sản phẩm</th>
                        <th>Ảnh</th>
                        <th>Tên sản phẩm</th>
                        <th>Thể loại</th>
                        <th>Giảm giá (%)</th>
                        <th>Thời gian bắt đầu</th>
                        <th>Thời gian kết thúc</th>
                        <th class="d-flex align-center justify-content-center">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${productSale}">
                        <tr>
                            <th scope="row">${  item.id }</th>
                            <td>${  item.srcImage }</td>
                            <td>${  item.productName }</td>
                            <td>${  item.productType.id }</td>
                            <td>${  item.sale.pcent }</td>
                            <td>${  item.sale.timeStart }</td>
                            <td>${  item.sale.timeEnd }</td>

                            <td class="d-flex align-center justify-content-center">
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

