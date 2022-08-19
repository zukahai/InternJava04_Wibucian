<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<base href="/">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../admin/includes/hd.jsp"></jsp:include>
<jsp:include page="../../admin/includes/header.jsp"></jsp:include>
<jsp:include page="../../admin/includes/sidebar1.jsp"></jsp:include>
<jsp:include page="../../admin/includes/container.jsp"></jsp:include>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--<c:set var="totalNormalRequest" value="${t}"/>--%>

<div class="content flex-column-fluid" id="kt_content">
	<div class="card mb-5 mb-xl-10" id="kt_profile_details_view">
		<c:choose>
			<c:when test="${isAlreadyApproved eq true or (isInShiftApproveTime eq false and isInShiftRequestTime eq false)}">
				<div class="card-body p-9 fs-2 text-center">Xin lỗi, hiện tại không phải
					là thời gian đăng ký ca làm việc
				</div>
			</c:when>
			<c:otherwise>
				<!--begin::Card header-->
				<div class="card-header flex-row">
					<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px">
						<!--begin::Card title-->
						<div class="card-title m-0">
							<h3 class="fw-bold m-0">Đăng ký ca làm việc</h3>
						</div>
						<div class="card-title mt-2">
							<p class="fst-italic m-0 fw-bold">Thời gian: từ ${weekStart}
								đến ${weekEnd} </p>
						</div>
					</div>
					<div class="d-flex justify-content-center align-items-lg-start flex-column h-100px my-10">
						<div class="form-check form-check-custom form-check-success form-check-solid mb-3">
							<input class="form-check-input mx-5"
								   type="checkbox"
								   disabled checked
							/> Đăng ký chính thức
						</div>
						<div class="form-check form-check-custom form-check-warning form-check-solid mb-3">
							<input class="form-check-input mx-5"
								   type="checkbox"
								   disabled checked
							/> Đăng ký dự bị
						</div>
						<div class="form-check form-check-custom form-check-warning form-check-solid mb-3">
							<input class="form-check-input mx-5"
								   type="checkbox"
								   disabled checked
								   style="background: gray;border:1px solid gray"
							/> Ca làm việc đã đủ slot
						</div>
						<div class="form-check form-check-custom form-check-solid">
							<input class="form-check-input mx-5"
								   type="checkbox"
								   disabled
							/> Ca làm việc có thể đăng ký
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
												<c:set var="work" value="${true}"/>
												<c:forEach var="workPlan"
														   items="${workPlansForNextWeek[day][shiftOfDay]}">
													<c:if test="${workPlan.work eq false}">
														<c:set var="work"
															   value="${false}"/>
														<div class="text-danger">Nghỉ
														</div>
													</c:if>
												</c:forEach>
												<c:if test="${work eq true}">
													<c:set var="canManipulate"
														   value="false"/>
													<c:forEach var="shiftNo"
															   items="${[0, 1]}">
														<c:if test="${canManipulate eq false}">
															<c:set var="shift"
																   value="${shiftRequestsForNextWeek[day][shiftOfDay][shiftNo]}"/>
															<c:choose>
																<c:when test="${empty shift}">
																	<c:set var="canManipulate"
																		   value="${true}"/>
																	<c:set var="shiftId"
																		   value=""/>
																</c:when>
																<c:otherwise>
																	<c:choose>
																		<c:when test="${shift.employee.id eq employeeId}">
																			<c:set var="shiftId"
																				   value="${shift.id}"/>
																			<c:set var="canManipulate"
																				   value="${true}"/>
																			<c:set var="isChecked"
																				   value="${true}"/>
																			<c:set var="colorClass">
																				<c:choose>
																					<c:when test="${shift.overtimeRequest eq false}">
																						${"form-check-success"}
																					</c:when>
																					<c:otherwise>
																						${"form-check-warning"}
																					</c:otherwise>
																				</c:choose>
																			</c:set>
																		</c:when>
																		<c:when test="${shift.overtimeRequest eq true and totalNormalRequest < 3}">
																			<c:set var="shiftId"
																				   value=""/>
																			<c:set var="canManipulate"
																				   value="${true}"/>
																			<c:set var="isChecked"
																				   value="${false}"/>
																		</c:when>
																	</c:choose>
																</c:otherwise>
															</c:choose>
														</c:if>
													</c:forEach>
													<div class="form-check form-check-custom ${colorClass} form-check-solid">
														<input class="form-check-input shift-request-checkbox"
															   shiftId="${shiftId}"
															   shiftDay="${day.value}"
															   shiftCode="${shiftOfDay.value}"
															   type="checkbox"
<%--																<c:if test="${isInShiftApproveTime eq true}">--%>
<%--																	disabled--%>
<%--																</c:if>--%>
																<c:choose>
																	<c:when test="${canManipulate eq false}">
																		disabled
																		style="background: gray; border: 1px solid gray;"
																	</c:when>
																	<c:when test="${not empty shift and (shift.employee.id eq employeeId)}">
																		checked
																	</c:when>
																</c:choose>
														/>
													</div>
												</c:if>
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

    const deleteShift = async (shiftID) => {
        const url = "${contextPath}/staff/shift/request/" + shiftID
        const response = await fetch(url, {
            method: "DELETE"
        })
        const responseData = await response.json()
        console.log(responseData)
        // status khác 200 nghĩa là xảy ra lỗi
        if (response.status !== 200) {
            return {
                success: false,
                error  : {
                    message: "Failed"
                },
            }
        }
        return {
            success: true,
        }
    }

    const createShift = async (idEmployee, shiftDate, shiftCode) => {
        const url = "${contextPath}/staff/shift/request"
        const response = await fetch(url, {
            method: "POST",
            body  : new URLSearchParams({
                                            shiftDate: weekDayMapping[shiftDate],
                                            shiftCode
                                        })
        })
        const responseData = await response.text()
        console.log(responseData)
        // status khác 200 nghĩa là xảy ra lỗi
        if (response.status !== 200) {
            return {
                success: false,
                error  : {
                    message: "Failed"
                },
            }
        }
        return {
            success: true,
            data   : {
                shift: JSON.parse(responseData)
            }
        }
    }
</script>
<script>
    // TODO: query all checkbox input elements and add input handler
    const checkboxShiftInputElements = document.querySelectorAll(
        ".shift-request-checkbox");
    checkboxShiftInputElements.forEach(input => {
        input.addEventListener("click", async (e) => {
            e.preventDefault()
            const isChecked = input.checked
            const shiftId = input.getAttribute("shiftid")
            const shiftDate = input.getAttribute("shiftday")
            const shiftCode = input.getAttribute("shiftcode")

            if (isChecked && shiftId === "") {
                // check -> tạo mới
                const {
                    error,
                    data
                } = await createShift("", shiftDate, shiftCode)
                if (error) {
                    window.alert("Create failed")
                    return
                }
                setTimeout(() => {
                    input.checked = true
                })
                console.log(data)
                input.setAttribute("shiftid", data.shift.id)
                if (data.shift.overtimeRequest) {
                    input.parentElement.classList.remove("form-check-success")
                    input.parentElement.classList.add("form-check-warning")
                    return
                }
                input.parentElement.classList.remove("form-check-warning")
                input.parentElement.classList.add("form-check-success")
                return
            }

            // unchecked -> delete shift
            if (!isChecked && shiftId) {
                const {
                    error,
                    data
                } = await deleteShift(shiftId)
                if (error) {
                    window.alert("Create failed")
                    return
                }
                setTimeout(() => {
                    input.checked = false
                })
                input.setAttribute("shiftid", "")
                input.parentElement.classList.remove("form-check-warning")
                input.parentElement.classList.remove("form-check-success")
            }
        })
    })
</script>
<jsp:include page="../../admin/includes/footer.jsp"></jsp:include>
<jsp:include page="../../admin/includes/end.jsp"></jsp:include>

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


