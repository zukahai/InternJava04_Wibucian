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
                        <h3 class="fw-bold m-0">Thêm bàn</h3>
                    </div>
                    <!--end::Card title-->
                    <!--begin::Action-->
                    <a href="admin/table/" class="btn btn-primary align-self-center">Danh sách bàn</a
            >
            <!--end::Action-->
        </div>
        <!--begin::Card header-->
        <!--begin::Card body-->
        <div class="card-body p-9">
            <form action="/admin/table/edit" method="post">
                <div class="rounded border p-5">
                    <div class="form-floating my-5">
                        <input type="idTablecf" class="form-control" id="idTablecf" name="idTablecf" placeholder="0" value="${tablecf.id}" readonly/>
                        <label for="idTablecf">Mã bàn</label>
                    </div>
                    <div class="form-floating my-5">
                        <select class="form-select" data-control="select2" id="idTypeTable" name="idTypeTable" data-placeholder="Select an option">
                            <c:forEach var="item" items="${typeTables}">
                                <option value="${item.id}" ${(item.id == tablecf.typeTable.id) ? "selected" : ""}> ${item.id} - ${item.typeName} </option>
                            </c:forEach>
                        </select>
                        <label for="idTypeTable">Loại bàn</label>
                    </div>
                    <div class="form-floating my-5">
                        <input type="capacity" class="form-control" id="capacity" name="capacity" placeholder="0" value="${tablecf.capacity}"/>
                        <label for="capacity">Sức chứa</label>
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" id="describe" name="describe" placeholder="" rows="6" placeholder="Mô tả">${tablecf.describe}</textarea>
                    </div>
                </div>

                <div class="text-center my-5">
                    <button class="btn btn-success" type="submit">Sửa</button>
                </div>
            </form>
        </div>
        <!--end::Card body-->
    </div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>