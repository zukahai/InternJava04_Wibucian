<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="vi_VN"/>
<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
    <!--begin::Card header-->
    <div class="card-header cursor-pointer">
        <!--begin::Card title-->
        <div class="card-title m-0">
            <h3 class="fw-bold m-0">Order</h3>
        </div>
        <!--end::Card title-->
        <!--begin::Action-->
        <span class="btn btn-primary align-self-center save-data">Lưu thông tin</span>
        <!--end::Action-->
    </div>
    <!--begin::Card header-->
    <!--begin::Card body-->
    <div class="card-body p-9">
        <!--begin::Wrapper-->


        <h3>Danh sách sản phẩm order</h3>
        <div class="d-flex flex-stack ">

            <table class="table" id="tabel-invoice">
                <!--begin::Table head-->
                <thead>
                <!--begin::Table row-->
                <tr class="text-start text-muted fw-bold fs-7 text-uppercase gs-0">
                    <th scope="col" class="min-w-100px">Id Hoá Đơn</th>
                    <th scope="col" class="min-w-100px">Nhân viên</th>
                    <th scope="col" class="min-w-100px">Tên Bàn</th>
                    <th scope="col" class="min-w-100px">Tên khác hàng</th>
                    <th scope="col" class="min-w-100px">Tổng tiền</th>
                    <th scope="col" class="min-w-100px">Trạng thái</th>
                    <th scope="col" class="min-w-100px">Hành động</th>
                </tr>
                <!--end::Table row-->
                </thead>

                <tbody class="text-gray-600 fw-semibold">
                <c:forEach items="${listInvoice}" var="item">
                    <tr class="text-start">
                        <td>${item.id}</td>
                        <td>${item.employee.name}</td>
                        <td>${item.groupTable.groupName}</td>
                        <td>${item.customerName}</td>
                        <td><fmt:formatNumber pattern="#,###" value="${item.totalMoney}"/> ₫</td>
                        <td>
                            <c:if test="${item.status == 1}">
                                <span class="badge badge-danger">Chưa thanh toán</span>
                            </c:if>
                            <c:if test="${item.status == 2}">
                                <span class="badge badge-success">Đã thanh toán</span>
                            </c:if>
                        </td>
                        <td class="text-center">
                            <a href="/admin/invoice/detail/${item.id}" data-action="${item.id}"
                               class="btn btn-icon btn-primary  btn-sm btn-icon-md btn-circle"
                               data-toggle="tooltip" data-placement="top" title="Sửa">
                                <i class="fa fa-edit"></i>
                            </a>

                            <a href="/admin/invoice/bill/${item.id}" data-action="${item.id}"
                               class="btn btn-icon btn-success  btn-sm btn-icon-md btn-circle"
                               data-toggle="tooltip" data-placement="top" title="Thanh Toán">
                                <i class="fa fa-money-bill"></i>
                            </a>

                            <span data-action="${item.id}"
                                  class="btn btn-icon btn-danger delete-btn btn-sm btn-icon-md btn-circle"
                                  data-toggle="tooltip" data-placement="top" title="Xóa">
                                <i class="fa fa-trash"></i>
                            </span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <ul class="pagination">
            <c:if test="${ allPage > 1}">
                <c:if test="${ currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="/admin/invoice/${currentPage - 1}?limit=${limit}">
                            <i class="fa fa-angle-left"></i>
                        </a>
                    </li>
                </c:if>
                <c:if test="${ currentPage == 1}">
                    <li class="page-item disabled">
                        <a class="page-link" href="/admin/invoice/${currentPage - 1}?limit=${limit}">
                            <i class="fa fa-angle-left"></i>
                        </a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="1" end="${allPage}">
                    <c:if test="${ i == currentPage}">
                        <li class="page-item active"><a href="/admin/invoice/${i}?limit=${limit}" class="page-link">${i}</a></li>
                    </c:if>
                    <c:if test="${ i != currentPage}">
                        <li class="page-item"><a href="/admin/invoice/${i}?limit=${limit}" class="page-link">${i}</a></li>
                    </c:if>
                </c:forEach>
                <c:if test="${ currentPage == allPage}">
                    <li class="page-item disabled">
                        <a class="page-link" href="/admin/invoice/${currentPage + 1}?limit=${limit}">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </c:if>
                <c:if test="${ currentPage < allPage}">
                    <li class="page-item ">
                        <a class="page-link" href="/admin/invoice/${currentPage + 1}?limit=${limit}">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </li>
                </c:if>
            </c:if>
        </ul>
    </div>
</div>

</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>
<script>

    $(document).on("click", ".delete-btn", function () {
        var id = $(this).attr("data-action");
        var url = "/admin/invoice/" + id;
        var that = $(this);
        swal.fire({
            title: 'Bạn có chắc chắn muốn xóa?',
            text: "Bạn sẽ không thể khôi phục lại dữ liệu này!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Vâng, xóa nó!',
            cancelButtonText: 'Không, hủy bỏ!'
        }).then((result) => {
            if (result.value) {
                $.ajax({
                    url: url,
                    type: "DELETE",
                    success: function (resutl) {
                        console.log(resutl);
                        if (resutl.check == true) {
                            toastr.success(resutl.value);
                            that.closest("tr").remove();
                        } else {
                            toastr.error(resutl.value);
                        }
                    }
                });
            }
        });
    });



</script>