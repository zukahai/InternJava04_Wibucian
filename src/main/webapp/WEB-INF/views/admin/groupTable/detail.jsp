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
                <h3 class="fw-bold m-0">Nhóm bàn: </h3>
                <h2 class="text-info">&nbsp ${groupTable.groupName}</h2>
                <h2>&nbsp [${groupTable.id}]</h2>
            </div>
            <!--end::Card title-->
            <!--begin::Action-->
            <a
                    href="admin/groupTable/"
                    class="btn btn-primary align-self-center"
            >Danh sách nhóm bàn</a
            >
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">
            <c:if test="${tablecfs.size() > 0}">
                <form action="/admin/detailGroupTable/view/${groupTable.id}" method="post">
                    <div class="rounded border row  d-flex justify-content-between h-100">
                        <div class="col col-10 form-floating my-5">
                            <select class="form-select" data-control="select2" id="idTable" name="idTable" data-placeholder="Select an option">
                                <c:forEach var="item" items="${tablecfs}">
                                    <option value="${item.id}">${item.id} - ${item.typeTable.typeName}</option>
                                </c:forEach>
                            </select>
                            <label for="idTable">Chọn bàn</label>
                        </div>
                        <div class="col text-center justify-content-center align-items-center d-inline-flex p-2">
                            <button class="btn btn-primary align-middle" type="submit">Thêm</button>
                        </div>
                    </div>
                </form>
            </c:if>

            <!--end::Input group-->
            <div class="table-responsive">
                <table class="table table-row-bordered gy-5">
                    <thead>
                    <tr class="fw-bold fs-6 text-gray-800">
                        <%--                        <th class="table-sort-desc">Mã loại bàn</th>--%>
                        <th>Mã bàn</th>
                        <th>Loại bàn</th>
                        <th>Thời gian gộp nhóm</th>
                        <th class="d-flex align-center justify-content-center">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${tablecfList}">
                        <tr>
                            <th scope="row">${  item.tablecf.id }</th>
                            <th scope="row">${  item.tablecf.typeTable.typeName }</th>
                            <td>${ item.groupTime } </td>
                            <td class="d-flex align-center justify-content-center">
                                <span class="btn btn-icon btn-danger delete-btn btn-sm btn-icon-md btn-circle"
                                      data-toggle="tooltip" data-placement="top" data-id="${item.id}" title="Xóa">
                                    <i class="fa fa-trash"></i>
                                </span>
                            </td>
                            <!--end::Action=-->
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--Begin::Pagination--%>
                <c:if test="${totalPage > 1}">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:if test="${page > 1}">
                                <li class="page-item">
                                    <a class="page-link" href="/admin/detailGroupTable/view/${groupTable.id}/page/${page - 1}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                </li>
                                <li class="page-item"><a class="page-link"  href="/admin/detailGroupTable/view/${groupTable.id}/page/${page - 1}" >${page - 1}</a></li>
                            </c:if>
                            <li class="page-item active"><a class="page-link" href="#" >${page}</a></li>
                            <c:if test="${page < totalPage}">
                                <li class="page-item"><a class="page-link"  href="/admin/detailGroupTable/view/${groupTable.id}/page/${page + 1}" >${page + 1}</a></li>
                                <li class="page-item">
                                    <a class="page-link"  href="/admin/detailGroupTable/view/${groupTable.id}/page/${page + 1}"  aria-label="Next">
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
                    url: "/admin/detailGroupTable/delete/" + id,
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