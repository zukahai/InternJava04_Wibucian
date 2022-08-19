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
                        <h3 class="fw-bold m-0">Dach sách loại bàn</h3>
                    </div>
                    <!--end::Card title-->
                    <!--begin::Action-->
                    <a href="admin/typeTable/" class="btn btn-primary align-self-center">Danh sách loại bàn</a
            >
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">
            <form action="/admin/typeTable/create" method="post" onsubmit="return validateForm()" name="Form" required>
                <div class="rounded border p-5">
                    <div class="form-floating my-5">
                        <input type="text" class="form-control" id="typeName" name="typeName" placeholder="VIP"/>
                        <label for="typeName">Tên loại bàn</label>
                    </div>
                    <div class="form-floating my-5">
                        <input type="number" class="form-control" id="price" name="price" placeholder="0" value="0" min="0"/>
                        <label for="price">Giá</label>
                    </div>

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


<script type="text/javascript">
    function validateForm() {
        var a = document.forms["Form"]["typeName"].value;
        var b = document.forms["Form"]["price"].value;
        console.log(a + " " + b);
        if (a == null || a == "" || b == null || b == "") {
            alert("Please Fill All Required Field");
            return false;
        }
        return true;
    }
</script>
