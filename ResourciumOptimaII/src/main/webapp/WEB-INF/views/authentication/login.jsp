<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head>
    <title>Login</title>
    <jsp:include page="../components/head.jsp" />
</head>


<body>
<!-- <body data-layout="horizontal"> -->
<div class="auth-page">
    <div class="container-fluid p-0">
        <div class="row g-0">
            <div class="col-xxl-3 col-lg-4 col-md-5">
                <div class="auth-full-page-content d-flex p-sm-5 p-4">
                    <div class="w-100">
                        <div class="d-flex flex-column h-100">
                            <c:if test="${not empty sessionScope.errorMessage}">
                                <div class="alert alert-danger">
                                        ${sessionScope.errorMessage}
                                </div>
                            </c:if>
                            <c:if test="${not empty sessionScope.success}">
                                <div class="alert alert-success">
                                        ${sessionScope.success}
                                </div>
                            </c:if>
                            <div class="mb-4 mb-md-5 text-center">
                                <a href="" class="d-block auth-logo">
                                    <img src="${pageContext.request.contextPath}/resources/images/logo-sm.svg" alt="" height="227"> <span class="logo-txt"></span>
                                </a>
                            </div>
                            <div class="auth-content my-auto">
                                <div class="text-center">
                                    <h5 class="mb-0">Welcome Back !</h5>
                                </div>
                                <form class="custom-form mt-4 pt-2" action="${pageContext.request.contextPath}/login" method="post">
                                    <div class="mb-3">
                                        <label class="form-label">Email</label>
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
                                    </div>
                                    <div class="mb-3">
                                        <div class="d-flex align-items-start">
                                            <div class="flex-grow-1">
                                                <label class="form-label">Password</label>
                                            </div>
                                            <div class="flex-shrink-0">
                                                <div class="">
                                                    <a href="" class="text-muted">Forgot password?</a>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="input-group auth-pass-inputgroup">
                                            <input type="password" class="form-control" placeholder="Enter password" name="password" aria-label="Password" aria-describedby="password-addon">
                                            <button class="btn btn-light shadow-none ms-0" type="button" id="password-addon"><i class="mdi mdi-eye-outline"></i></button>
                                        </div>
                                    </div>
                                    <div class="row mb-4">
                                        <div class="col">
                                            <div class="form-check">
                                                <input class="form-check-input" type="checkbox" id="remember-check">
                                                <label class="form-check-label" for="remember-check">
                                                    Remember me
                                                </label>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="mb-3">
                                        <button class="btn btn-primary w-100 waves-effect waves-light" type="submit">Log In</button>
                                    </div>
                                </form>


                                <div class="mt-5 text-center">
                                    <p class="text-muted mb-0">Don't have an account ? <a href=""
                                                                                          class="text-primary fw-semibold"> Signup now </a> </p>
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

<jsp:include page="../components/scripts.jsp" />

<!-- JAVASCRIPT -->
<script src="${pageContext.request.contextPath}/resources/libs/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/metismenu/metisMenu.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/simplebar/simplebar.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/node-waves/waves.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/libs/feather-icons/feather.min.js"></script>
<!-- pace js -->
<script src="${pageContext.request.contextPath}/resources/libs/pace-js/pace.min.js"></script>
<!-- password addon init -->
<script src="${pageContext.request.contextPath}/resources/js/pages/pass-addon.init.js"></script>

</body>
</html>