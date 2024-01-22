@import exercise.dto.posts.EditPostPage
@import exercise.util.NamedRoutes
@param EditPostPage page

@template.layout.page(
content = @`
    @if (page.getErrors() != null)
        <div class="container">
            @for (var errors : page.getErrors().values())
                @for (var error : errors)
                    <div class="alert alert-danger" role="alert">
                        ${error.getMessage()}
                    </div>
                @endfor
            @endfor
        </div>
    @endif
    <div class="container">
        <form action="${NamedRoutes.postPath(page.getPost().getId())}" method="POST">
            <div class="form-group">
                <label for="formControlInput">Post name</label>
                <input name="name" type="text" class="form-control" id="formControlInput"
                       value="${page.getPost().getName()}">
            </div>
            <div class="form-group">
                <label for="formControlTextarea">Body post</label>
                <textarea name="body" class="form-control" id="formControlTextarea" rows="10"
                          placeholder="${page.getPost().getBody()}"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
`
)
