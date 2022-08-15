<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../includes/hd.jsp"></jsp:include>
<jsp:include page="../includes/header.jsp"></jsp:include>
<jsp:include page="../includes/sidebar.jsp"></jsp:include>
<jsp:include page="../includes/container.jsp"></jsp:include>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--<c:set var="totalNormalRequest" value="${t}"/>--%>

<div class="content flex-column-fluid" id="kt_content">
	<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
		<c:choose>
			<c:when test="${isInWorkPlanTime eq false}">
				<div class="card-body p-9 fs-2 text-center">Xin lỗi, hiện tại không phải
					là thời gian lên kế hoạch làm việc
				</div>
			</c:when>
			<c:otherwise>
				<!--begin::Card header-->
				<div class="card-header flex-row">
					<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px">
						<!--begin::Card title-->
						<div class="card-title m-0">
							<h3 class="fw-bold m-0">Kế hoạch làm việc</h3>
						</div>
						<div class="card-title mt-2">
							<p class="fst-italic m-0 fw-bold">Thời gian: từ ${weekStart}
								đến ${weekEnd} </p>
						</div>
					</div>
					<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px fs-4">
						<div>
							<div class="form-check form-check-custom form-check-success form-check-solid mb-2">
								<input class="form-check-input mx-5"
									   type="checkbox"
									   checked disabled
								/> Có làm việc
							</div>
							<div class="form-check form-check-custom form-check-success form-check-solid">
								<input class="form-check-input mx-5"
									   type="checkbox"
									   disabled
								/> Nghỉ làm việc
							</div>
						</div>
					</div>
				</div>
				<div class="card-body p-9">
					<!--end::Input group-->
					<div class="table-responsive">
						<table class="table table-row-bordered table-striped gy-5">
							<thead>
								<tr class="fw-bold fs-6 text-gray-800 row row-cols-4">
									<th class="col text-center"></th>
									<th class="col text-center fw-bold">Sáng</th>
									<th class="col text-center fw-bold">Chiều</th>
									<th class="col text-center fw-bold">Tối</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="day" items="${daysOfWeek}">
									<tr class="row row-cols-4">
										<td class="col d-flex align-items-center justify-content-center fw-bold">${day}</td>
										<c:forEach var="shiftOfDay"
												   items="${shiftsOfDay}">
											<td class="col d-flex flex-column gap-5 align-items-center justify-content-center">
												<c:set var="canManipulate" value="false"/>
												<c:forEach var="workPlan"
														   items="${workPlansForNextWeek[day][shiftOfDay]}">
													
													<div class="form-check form-check-custom form-check-success form-check-solid">
														<input class="form-check-input work-plan-checkbox"
															   workPlanId="${workPlan.id}"
															   type="checkbox"
																<c:if test="${workPlan.work eq true}">
																	checked</c:if>
														/>
													</div>
												</c:forEach>
											</td>
										</c:forEach>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!--end::Card body-->
			</c:otherwise>
		
		</c:choose>
	</div>
	
	<!--end::Dropdown wrapper-->
</div>
<script>
    let timeoutHolder = null;

    const weekDayMapping = {}
    <c:forEach items="${weekDayMapping}" var="item">
    weekDayMapping[${item.key}] = "${item.value}"
    </c:forEach>

    const updateWorkPlan = async (workPlanId, work) => {
        const url = "${contextPath}/admin/workPlan/" + workPlanId
        const response = await fetch(url, {
            method: "PATCH",
            body: new URLSearchParams({
                                          work
                                      })
        })
        const responseData = await response.json()
        console.log(responseData)
        // status khác 200 nghĩa là xảy ra lỗi
        if (response.status !== 200) {
            return {
                success: false,
                error: {
                    message: "Failed"
                },
            }
        }
        return {
            success: true,
        }
    }
</script>
<script>
    // TODO: query all checkbox input elements and add input handler
    const checkboxWorkPlanInputElements = document.querySelectorAll(
        ".work-plan-checkbox");
    checkboxWorkPlanInputElements.forEach(input => {
        input.addEventListener("change", (e) => {
            // e.preventDefault()
			console.log(e)
		})
        input.addEventListener("click", async (e) => {
            e.preventDefault()
            const isChecked = input.checked
            const workPlanId = input.getAttribute("workplanid")

            const {
                error,
                data
            } = await updateWorkPlan(workPlanId, isChecked)
			
            if (error) {
                window.alert("Update failed")
                return
            }
            
            setTimeout(() => {
                input.checked = isChecked
			})
        })
    })
</script>
<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/end.jsp"></jsp:include>

<%--<div class="menu-item px-3 bg-secondary">--%>
<%--	<div--%>
<%--			class="menu-link px-3 employee-select"--%>
<%--			id="dropdown-employee${day.value}${shiftOfDay.value}${shiftNo}"--%>
<%--			shiftId="${shiftId}"--%>
<%--			dayOfWeek="${day.value}"--%>
<%--			shiftOfDay="${shiftOfDay.value}"--%>
<%--			shiftNo="${shiftNo}"--%>
<%--			employeeId="${employeeIdForSelect}"--%>
<%--	>--%>
<%--		${employeeIdForSelect}--%>
<%--	</div>--%>
<%--</div>--%>

<%--<div class="menu-item px-3">--%>
<%--	<div class="menu-content fs-6 text-dark fw-bold px-3 py-2 text-center">${employeeId}</div>--%>
<%--</div>--%>


