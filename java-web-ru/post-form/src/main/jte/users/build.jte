@template.layout.page(
content = @`
    <form action="/users" method="POST">
        <div class="form-group">
            <label for="exampleInputEmail1">First name</label>
            <input name="firstName" type="text" class="form-control" aria-describedby="name" placeholder="Your firstname" required>
        </div>

        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input name="lastName" type="text" class="form-control" aria-describedby="name" placeholder="Your lastname" required>
        </div>

        <div class="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input name="email" type="email" class="form-control" aria-describedby="email" placeholder="Enter email" required>
        </div>

        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input name="password" type="password" class="form-control" placeholder="Password" required>
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
`
)