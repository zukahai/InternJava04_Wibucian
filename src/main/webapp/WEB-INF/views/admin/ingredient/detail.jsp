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
                <h3 class="fw-bold m-0">Nguyên liệu:</h3>
                <h2 class="text-info mx-2">${Ingredient.ingredientName}</h2>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <a
                    href="admin/ingredient/"
                    class="btn btn-primary align-self-center"
            >Quay về kho nguyên liệu</a
            >
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">

            <div class="table-responsive">
                <table class="table table-row-bordered gy-5">
                    <thead>
                    <tr class="fw-bold fs-6 text-gray-800">
<%--                        <th class="table-sort-desc">Mã loại bàn</th>--%>
                        <th class="d-flex align-center justify-content-center"></th>
                        <th>Thời gian</th>
                        <th class="text-center">Giá trị thay đổi (${Ingredient.unit})</th>
                        <th>Nội dung thay đổi</th>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${detailIngredients}">
                        <tr>
                            <td class="justify-content-center d-flex">
                                <c:if test="${item.valueChange >= 0}">
                                    <span class="badge badge-success p-2">Tăng</span>
                                </c:if>
                                <c:if test="${item.valueChange < 0}">
                                    <span class="badge badge-danger p-2">Giảm</span>
                                </c:if>
                            </td>
                            <th>${  item.formatTime() }</th>
                            <c:if test="${item.valueChange >= 0}">
                                <td class="text-center font-weight-bold text-success">${ item.valueChange }  </td>
                            </c:if>
                            <c:if test="${item.valueChange < 0}">
                                <td class="text-center font-weight-bold text-danger">${ -item.valueChange } </td>
                            </c:if>

                            <td>${ item.content } </td>

                            <!--end::Action=-->
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!--end::Card body-->
        <%--Begin::Pagination--%>
        <c:if test="${totalPage > 1}">
            <nav aria-label="Page navigation example">
                <ul class="pagination">
                    <c:if test="${page > 1}">
                        <li class="page-item">
                            <a class="page-link" href="/admin/groupTable/page/${page - 1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <li class="page-item"><a class="page-link"  href="/admin/groupTable/page/${page - 1}" >${page - 1}</a></li>
                    </c:if>
                    <li class="page-item active"><a class="page-link" href="#" >${page}</a></li>
                    <c:if test="${page < totalPage}">
                        <li class="page-item"><a class="page-link"  href="/admin/groupTable/page/${page + 1}" >${page + 1}</a></li>
                        <li class="page-item">
                            <a class="page-link"  href="/admin/groupTable/page/${page + 1}"  aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </nav>
        </c:if>
        <%--End::Pagination--%>
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
            title: "Bạn có chắc chắn muốn xóa nhóm bàn này không?",
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
                    url: "/admin/groupTable/delete/" + id,
                    type: "GET",
                    success: function (result) {
                        if (result.check === true) {
                            toastr.success("Xóa thành công");
                            row.remove();
                        } else {
                            toastr.error("Xóa thất bại: Nhóm bàn này đã được sử dụng");
                        }
                    }
                })
            }
        });


    });
    //handel on change id-select-product

    //handle on click delete-btn
    $(document).on("click", ".delete-btn-detail", function () {
        // var row = $(this).closest("tr");
        var id = $(this).attr("data-id");
        console.log(id);

        swal.fire({
            title: "Bạn có chắc chắn muốn xóa dữ liệu trong nhóm bàn này không?",
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
                    url: "/admin/groupTable/deleteDetail/" + id,
                    type: "GET",
                    success: function (result) {
                        if (result.check === true) {
                            toastr.success("Xóa thành công");
                            row.remove();
                        } else {
                            toastr.error("Xóa thất bại: Nhóm bàn này đã được sử dụng");
                        }
                    }
                })
            }
        });
    });
    //handel on change id-select-product
</script>


