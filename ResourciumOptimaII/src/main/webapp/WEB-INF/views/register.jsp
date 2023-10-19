<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 10/17/2023
  Time: 11:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head lang="en">
    <title>Register</title>
    <jsp:include page="head.jsp" />
</head>

<body>

<div class="auth-page">
    <div class="container-fluid p-0">
        <div class="row g-0">
            <div class="col-xxl-3 col-lg-4 col-md-5">
                <div class="auth-full-page-content d-flex p-sm-5 p-4">
                    <div class="w-100">
                        <div class="d-flex flex-column h-100">
                            <div class="mb-4 mb-md-5 text-center">
                                <a href="" class="d-block auth-logo">
                                    <img src="${pageContext.request.contextPath}/resources/images/logo-sm.svg" alt="" height="28"> <span class="logo-txt">ResourciumOptima</span>
                                </a>
                            </div>
                            <div class="auth-content my-auto">
                                <div class="text-center">
                                    <h5 class="mb-0">Register Account</h5>
                                </div>
                                <form class="needs-validation custom-form mt-4 pt-2" novalidate action="${pageContext.request.contextPath}/register" method="post">
                                    <div class="mb-3">
                                        <label for="mail" class="form-label">Email</label>
                                        <input type="email" class="form-control" name="email" id="mail" placeholder="Enter email" required>
                                        <div class="invalid-feedback">
                                            Please Enter Email
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="username" class="form-label">Username</label>
                                        <input type="text" class="form-control" name="username"  id="username" placeholder="Enter username" required>
                                        <div class="invalid-feedback">
                                            Please Enter Username
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="name" class="form-label">Name</label>
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Enter your name" required>
                                        <div class="invalid-feedback">
                                            Please Enter Name
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="surname" class="form-label">Surname</label>
                                        <input type="text" class="form-control" id="surname" name="surname" placeholder="Enter your surname" required>
                                        <div class="invalid-feedback">
                                            Please Enter Surname
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="password" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
                                        <div class="invalid-feedback">
                                            Please Enter Password
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="position" class="form-label">Position</label>
                                        <input type="text" class="form-control" id="position" name="position" placeholder="Enter your position" required>
                                        <div class="invalid-feedback">
                                            Please Enter Position
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label for="hire_date" class="form-label">Hiring Date</label>
                                        <input type="date" class="form-control" id="hire_date" name="date" placeholder="Enter the date" required>
                                        <div class="invalid-feedback">
                                            Please Enter Hiring date
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <button class="btn btn-primary w-100 waves-effect waves-light" type="submit">Register</button>
                                    </div>
                                </form>


                                <div class="mt-5 text-center">
                                    <p class="text-muted mb-0">Already have an account ? <a href="${pageContext.request.contextPath}/login"
                                                                                            class="text-primary fw-semibold"> Login </a> </p>
                                </div>
                            </div>
                            <div class="mt-4 mt-md-5 text-center">
                                <p class="mb-0"> <script>document.write(new Date().getFullYear())</script>  Crafted with <i class="mdi mdi-heart text-danger"></i> by Chkir Sbee3</p>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- end auth full page content -->
            </div>
            <!-- end col -->
            <div class="col-xxl-9 col-lg-8 col-md-7">
                <div class="auth-bg pt-md-5 p-4 d-flex">
                    <div class="bg-overlay bg-primary"></div>
                    <ul class="bg-bubbles">
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                        <li></li>
                    </ul>
                    <!-- end bubble effect -->
                </div>
            </div>
            <!-- end col -->
        </div>
        <!-- end row -->
    </div>
    <!-- end container fluid -->
</div>

<jsp:include page="scripts.jsp" />

<!-- JAVASCRIPT -->
<script src="${pageContext.request.contextPath}/resources/libs/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/metismenu/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/simplebar/simplebar.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/node-waves/waves.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/feather-icons/feather.min.js"></script>
<!-- pace js -->
<script src="${pageContext.request.contextPath}/resources/libs/pace-js/pace.min.js"></script>

<!-- validation init -->
<script src="${pageContext.request.contextPath}/resources/js/pages/validation.init.js"></script>

</body>
</html>
