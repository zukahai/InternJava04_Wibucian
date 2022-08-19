<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<style>
    .form-check-input {
        accent-color: #8b3c3c !important;
    }

    /*.form-check-custom.form-check-solid .form-check-input:checked {*/
    /*    background: var(--kt-form-check-input-checked-bg-color-solid);*/
    /*}*/
    /*.form-check-input:checked[type=checkbox] {*/
    /*    background-image: var(--kt-form-check-input-checked-bg-image);*/
    /*}*/
</style>

<div class="content flex-column-fluid" id="kt_content">
	<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
		<!--begin::Card header-->
		<div class="card-header flex-row">
			<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px">
				<!--begin::Card title-->
				<div class="card-title m-0">
					<h3 class="fw-bold m-0">Tính lương
					</h3>
				</div>
			</div>
		</div>
		<div class="card-body p-9">
			<div class="time-pick-wrapper pb-10 d-flex align-items-center justify-content-center">
				<h3>Tháng: ${currentMonth}</h3>
			</div>
			<div>
				<input type="text"
					   class="form-control form-control-solid w-200px bg-active employee-search"
					   placeholder="Tìm kiếm"/>
			</div>
			<!--end::Input group-->
			<div class="table-responsive">
				<form class="employee-select-form">
					<table id="kt_datatable_complex_header"
						   class="table table-striped table-row-bordered gy-5 gs-7 border rounded w-100">
						<thead>
							<tr class="fw-bold fs-6 text-gray-800 px-7">
								<th class="align-middle border-bottom w-20px">
								</th>
								<th class="align-middle text-center border-bottom border-end min-w-50px">
									STT
								</th>
								<th class="min-w-125px border-end text-center">Mã nhân
									viên
								</th>
								<th class="min-w-125px border-end text-center">Tên nhân
									viên
								</th>
							</tr>
						</thead>
					</table>
					<button class="btn btn-secondary disabled submit-btn d-block mx-auto mb-20">
						Tính lương
					</button>
				</form>
				<table id="salary-data-table"
					   class="table table-striped table-row-bordered gy-5 gs-7 border rounded w-100 mt-20">
					<thead>
						<tr class="fw-bold fs-6 text-gray-800 px-7">
							<th rowspan="2"
								class="align-middle text-center border-bottom border-end min-w-50px">
								STT
							</th>
							<th colspan="3" class="border-bottom border-end text-center">
								Nhân viên
							</th>
							<th colspan="2" class="border-bottom border-end text-center">
								Thông tin làm việc đến ngày hiện tại
							</th>
							<th rowspan="2" colspan="1"
								class="align-middle border-bottom border-end text-center min-w-150px">
								Tổng lương
							</th>
						</tr>
						<tr class="fw-bold fs-6 text-gray-800 px-7">
							<th class="min-w-125px border-end text-center">Mã nhân viên
							</th>
							<th class="min-w-125px border-end text-center">Tên nhân viên
							</th>
							<th class="min-w-125px border-end text-center">Hệ số lương
							</th>
							<th class="min-w-125px border-end text-center">Số ca đã làm
							</th>
							<th class="min-w-125px border-end text-center">Tổng giờ làm
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
    let selectedEmployeeIds = []
    const submitBtn = document.querySelector(".submit-btn")
    const form = document.querySelector(".employee-select-form")
    const setUpButtons = () => {

        const checkboxElements = form.querySelectorAll(".employee-check")

        checkboxElements.forEach(checkbox => {
            checkbox.addEventListener("click", () => {
                const preLength = selectedEmployeeIds.length
                if (checkbox.checked) {
                    selectedEmployeeIds.push(checkbox.value)
                } else {
                    selectedEmployeeIds =
                        selectedEmployeeIds.filter(
                            employeeId => employeeId !== checkbox.value)
                }
                if (selectedEmployeeIds.length === 0) {
                    submitBtn.classList.remove("btn-primary")
                    submitBtn.classList.remove("active")
                    submitBtn.classList.add("btn-secondary")
                    submitBtn.classList.add("disabled")
                } else {
                    if (preLength === 0) {
                        submitBtn.classList.add("btn-primary")
                        submitBtn.classList.add("active")
                        submitBtn.classList.remove("btn-secondary")
                        submitBtn.classList.remove("disabled")
                    }

                }
            })
        })
    }

    const dataTable = $("#kt_datatable_complex_header")
        .DataTable({
                       ajax        : {
                           url: "${contextPath}/admin/salary/employee"
                       },
                       columnDefs  : [
                           {
                               render        : (data, type, row) => {
                                   const employeeId = row.employeeId
                                   return `<div class="form-check form-check-custom form-check-solid">
                                            <input class="form-check-input employee-check" type="checkbox" value="` +
                                          employeeId + `"/>
                                   </div>`
                               },
                               defaultContent: "",
                               targets       : 0
                           },
                           {
                               orderable     : false,
                               defaultContent: "-",
                               targets       : 1,
                           },
                           {
                               data   : "employeeId",
                               targets: 2,
                           },
                           {
                               data   : "employeeName",
                               targets: 3,
                           },
                       ],
                       order       : [[2, 'asc']],
                       language    : {
                           emptyTable: "Không có dữ liệu"
                       },
                       drawCallback: function () {
                           setUpButtons()
                       },
                   });

    dataTable.on('order.dt', function () {
        let i = 1;

        dataTable.cells(null, 1, {order: 'applied'})
                 .every(function (cell) {
                     this.data(i++);
                 });
    })
             .draw();

    $('.employee-search')
        .on('keyup', function () {
            dataTable.search(this.value)
                     .draw();
        });

    const salaryDataTable = $("#salary-data-table")
        .DataTable({
                       <%--ajax      : {--%>
                       <%--    type  : "POST",--%>
                       <%--    url   : "${contextPath}/admin/salary/calculate",--%>
                       <%--    "data": function (d) {--%>
                       <%--        d.employeeIdList = selectedEmployeeIds;--%>
                       <%--    }--%>
                       <%--},--%>
                       columnDefs: [
                           {
                               orderable     : false,
                               defaultContent: "",
                               targets       : 0,
                           },
                           {
                               data   : "employeeId",
                               targets: 1
                           },
                           {
                               data   : "employeeName",
                               targets: 2,
                           },
                           {
                               data   : "employeeSalaryCoef",
                               targets: 3,
                           },
                           {
                               data   : "numOfShiftWorked",
                               targets: 4,
                           },
                           {
                               data   : "hoursWorked",
                               render : (data, type, row) => {
                                   return data.toFixed(1);
                               },
                               targets: 5,
                           },
                           {
                               data   : "totalSalary",
                               render : (data, type, row) => {
									return data.toFixed(2);
                               },
                               targets: 6,
                           },
                       ],
                       order     : [[1, 'asc']],
                       language  : {
                           emptyTable: "Không có dữ liệu"
                       }
                   });

    salaryDataTable.on('order.dt', function () {
        let i = 1;

        salaryDataTable.cells(null, 0, {order: 'applied'})
                       .every(function (cell) {
                           this.data(i++);
                       });
    })
                   .draw();


    form.addEventListener("submit", async (e) => {
        e.preventDefault()
        salaryDataTable.ajax
                       .url("${contextPath}/admin/salary/calculate/api?employeeIdList=" +
                            selectedEmployeeIds.join(","))
        salaryDataTable.ajax.reload()
        form.reset()
        selectedEmployeeIds.length = 0
        submitBtn.classList.remove("btn-primary")
        submitBtn.classList.remove("active")
        submitBtn.classList.add("btn-secondary")
        submitBtn.classList.add("disabled")
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

