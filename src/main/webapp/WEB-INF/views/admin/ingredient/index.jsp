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
                        <th>Trạng thái</th>

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
                            <td>${  item.expiryIngredient }</td>
                            <td class="d-flex align-center justify-content-center">
                                <a href="" class="btn btn-warning mx-1">Xem</a>
                                <a href="admin/ingredient/edit/${ item.id }" class="btn btn-success mx-1">Sửa</a>
                                <span data-id="${ item.id }" class="btn btn-danger mx-1 delete-btn">Xoá</span>
                            </td>
                            <!--end::Action=-->
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