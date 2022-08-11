<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.Instant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
                <h3 class="fw-bold m-0">Sản phẩm: </h3>
                <h2 class="text-info">&nbsp ${product.productName}</h2>
                <h2>&nbsp [${product.id}]</h2>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <a
                    href="admin/product/"
                    class="btn btn-primary align-self-center"
            >Danh sách sản phẩm</a
            >
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">
            <c:if test="${ingredients.size() > 0}">
            <form action="/admin/detailProduct/view/${product.id}" method="post">
                <div class="rounded border row  d-flex justify-content-between h-100">
                    <div class="col col-8 form-floating my-5">
                        <select class="form-select" data-control="select2" id="idIngredient" name="idIngredient" data-placeholder="Select an option">
                            <c:forEach var="item" items="${ingredients}">
                                <option value="${item.id}">${item.id} - ${item.ingredientName}    ( ${item.unit} )</option>
                            </c:forEach>
                        </select>
                        <label for="idIngredient">Nguyên liệu</label>
                    </div>
                    <div class="col col-2 form-floating my-5 text-center justify-content-center align-items-center d-inline-flex">
                        <input type="groupName" class="form-control" id="quantity" name="quantity" value="0"/>
                        <label for="quantity">Số lượng</label>
                    </div>
                    <div class="col col-2 text-center justify-content-center align-items-center d-inline-flex p-2">
                        <button class="btn btn-primary align-middle w-100 " type="submit">Thêm</button>
                    </div>
                </div>
            </form>
            </c:if>

            <div class="table-responsive">
                <table class="table table-row-bordered gy-5">
                    <thead>
                    <tr class="fw-bold fs-6 text-gray-800">
<%--                        <th class="table-sort-desc">Mã loại bàn</th>--%>
                        <th>Mã Nguyên liệu</th>
                        <th>Tên nguyên liệu</th>
                        <th>Đơn giá</th>
                        <th>Lượng</th>
                        <th class="text-center">Thành tiền (VND)</th>
                        <th class="d-flex align-center justify-content-center">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${detailProducts}">
                        <tr>
                            <th scope="row">${  item.ingredient.id }</th>
                            <th scope="row">${  item.ingredient.ingredientName }</th>
                            <th scope="row"><fmt:formatNumber value="${item.ingredient.price}" maxFractionDigits = "3" type="number"/> (VND/${ item.ingredient.unit})</th>
                            <th scope="row">${ item.quantity } ${ item.ingredient.unit }</th>
                            <th scope="row" class="text-center"><fmt:formatNumber value="${  item.ingredient.price * item.quantity}" maxFractionDigits = "3" type="number"/></th>
                            <td class="d-flex align-center justify-content-center">
                                <span class="btn btn-icon btn-danger delete-btn btn-sm btn-icon-md btn-circle"
                                      data-toggle="tooltip" data-placement="top" data-id="${item.id}" title="Xóa">
                                    <i class="fa fa-trash"></i>
                                </span>
                            </td>

                            <!--end::Action=-->
                        </tr>
                    </c:forEach>
                    <c:if test="${detailProducts.size() > 0}">
                        <tr>
                            <th class="text-center text-primary" colspan="4" scope="row">Tổng tiền thành phần của sản phẩm ${product.productName}:</th>
                            <th scope="row" class="text-center text-primary"><fmt:formatNumber value="${priceProduct}" maxFractionDigits = "3" type="number"/></th>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
                <h1 class="text-center">Giá bán ra: <span class="text-danger"><fmt:formatNumber value="${priceSell}" maxFractionDigits = "3" type="number"/> VND</span></h1>
            </div>
        </div>
        <!--end::Card body-->
    </div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>

<script>
    //handle on click delete-btn
    $(document).on("click", ".delete-btn", function () {
        var row = $(this).closest("tr");
        var id = $(this).attr("data-id");
        console.log(id);
        swal.fire({
            title: "Bạn có chắc chắn muốn xóa?",
            text: "Sau khi xóa, bạn sẽ không thể phục hồi dữ liệu này!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Đồng ý",
            cancelButtonText: "Hủy bỏ"
        }).then(function (result) {
            if (result.value) {
                $.ajax({
                    url: "/admin/detailProduct/delete/" + id,
                    type: "GET",
                    success: function (result) {
                        if (result.check === true) {
                            toastr.success("Xóa thành công");
                            window.location.reload();
                            row.remove();
                        } else {
                            toastr.error("Xóa thất bại");
                        }
                    }
                })
            }
        });
    });
    //handel on change id-select-product
</script>