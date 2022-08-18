<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <h3 class="fw-bold m-0">Dach sách nguyên liệu</h3>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <a
                    href="admin/ingredient/create/"
                    class="btn btn-primary align-self-center"
            >Thêm nguyên liệu</a
            >
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">

            <!--end::Input group-->
            <div class="table-responsive">
                <table id="table-tabel" class="table table-row-bordered gy-5">
                    <thead>
                    <tr class="fw-bold fs-6 text-gray-800">
                        <%--                        <th class="table-sort-desc">Mã loại bàn</th>--%>
                        <th>ID</th>
                        <th>Tên nguyên Liệu</th>
                        <th>Giá</th>
                        <th>Nguồn gốc</th>
                        <th>Đơn vị</th>
                        <th>Hạn sử dụng</th>

                        <th class="d-flex align-center justify-content-center">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${xemdanhsach}">
                        <tr>
                            <th class="id-tabel" scope="row">${ item.id }</th>
                            <td>${  item.ingredientName }</td>
                            <td>${  item.price }</td>
                            <td>${  item.origin }</td>
                            <td>${  item.unit }</td>

                            <td><fmt:formatDate pattern="dd-MM-yyyy"
                                                value="${  item.expiryIngredient }"/></td>
                            <td class="d-flex align-center justify-content-center">
                                <a href="admin/detailIngredient/view/${ item.id }" class="btn btn-primary mx-1">Xem chi tiết</a>
                                <a href="admin/ingredient/edit/${ item.id }" class="btn btn-success mx-1">Sửa</a>
                                <span data-id="${ item.id }" class="btn btn-danger mx-1 delete-btn">Xoá</span>
                            </td>
                            <!--end::Action=-->
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <c:if test="${totalPage > 1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:if test="${page > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="/admin/ingredient/page/${page - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                </li>
                                <li class="page-item"><a class="page-link" href="/admin/ingredient/page/${page - 1}">${page - 1}</a></li>
                            </c:if>
                            <li class="page-item active"><a class="page-link" href="#" >${page}</a></li>
                            <c:if test="${page < totalPage}">
                                <li class="page-item"><a class="page-link" href="/admin/ingredient/page/${page + 1}">${page + 1}</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="/admin/ingredient/page/${page + 1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </nav>
                </c:if>

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
                    url: "/admin/ingredient/delete/" + id,
                    type: "GET",
                    success: function (result) {
                        if (result.check === true) {
                            toastr.success("Xóa thành công");
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