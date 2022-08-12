<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="content flex-column-fluid" id="kt_content">
	<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
		<!--begin::Card header-->
		<div class="card-header flex-row">
			<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px">
				<!--begin::Card title-->
				<div class="card-title m-0">
					<h3 class="fw-bold m-0">Bảng điểm danh
					</h3>
				</div>
			</div>
		</div>
		<div class="card-body p-9">
			<div class="time-pick-wrapper pb-10 d-flex align-items-center justify-content-center">
				Ngày: <input type="date" max="${currentMonth}" class="date-input mx-5"/>
			</div>
			<!--end::Input group-->
			<div class="table-responsive">
				<table id="kt_datatable_complex_header"
					   class="table table-striped table-row-bordered gy-5 gs-7 border rounded w-100">
					<thead>
						<tr class="fw-bold fs-6 text-gray-800 px-7">
							<th rowspan="2"
								class="align-middle border-bottom border-end min-w-50px">
								STT
							</th>
							<th colspan="2" class="border-bottom border-end text-center">
								Nhân viên
							</th>
							<th colspan="2" class="border-bottom border-end text-center">
								Ca làm
							</th>
							<th rowspan="2" colspan="1"
								class="align-middle border-bottom border-end text-center min-w-150px">
								Điểm danh
							</th>
						</tr>
						<tr class="fw-bold fs-6 text-gray-800 px-7">
							<th class="min-w-125px border-end text-center">Mã nhân viên
							</th>
							<th class="min-w-125px border-end text-center">Tên nhân viên
							</th>
							<th class="min-w-125px border-end text-center">Mã ca làm
							</th>
							<th class="min-w-125px border-end text-center">Buổi
							</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<!--end::Card body-->
	</div>
</div>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>

<script>
    const dataTable = $("#kt_datatable_complex_header")
        .DataTable({
                       <%--ajax        : "${contextPath}/admin/shiftRotate/pending",--%>
                       columnDefs: [
                           {
                               orderable     : false,
                               defaultContent: "-",
                               targets       : 0,
                           },
                           {
                               data   : "employeeId",
                               targets: 1,
                           },
                           {
                               data   : "employeeName",
                               targets: 2,
                           },
                           {
                               data   : "shiftId",
                               targets: 3,
                           },
                           {
                               data   : "shiftCode",
							   render: (data) => {
                                   switch (data) {
									   case 1: return "Sáng";
                                       case 2: return "Chiều";
                                       case 3: return "Tối";
								   }
							   },
                               targets: 4,
                           },
                           {
                               data   : "rollCallAt",
							   render: (data) => {
								   if (!data) return ""
                                   return new Date(data).toLocaleString().split(", ")[1]
							   },
                               defaultContent: "",
                               targets: 5,
                           },
                       ],
                       order     : [[1, 'asc']],
                       language  : {
                           emptyTable: "Không có dữ liệu"
                       }
                   });

    dataTable.on('order.dt', function () {
        let i = 1;

        dataTable.cells(null, 0, {order: 'applied'})
                 .every(function (cell) {
                     this.data(i++);
                 });
    })
             .draw();

    const dateInput = document.querySelector(".date-input")
    dateInput.addEventListener("input", async (e) => {
        console.log(e.target.value)
        const date = e.target.value
        dataTable.ajax
                 .url("${contextPath}/admin/salary/roll-call/summarize?date=" + date)
        dataTable.ajax.reload()
    })
</script>

<style>
    th {
        padding: 0;

    }

    td {
        text-align: center;
    }
</style>

