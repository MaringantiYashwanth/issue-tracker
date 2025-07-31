# issue-tracker
issue tracking application that which helps teams and the users to assign the bugs and projects
This implementation provides:

## BugService Features:
- **CRUD operations** for bugs
- **Repository method implementations** for all the custom queries
- **Status management** with automatic comment logging
- **Assignment tracking** with history
- **Priority updates** with audit trail
- **Comment system** for bug discussions

## BugController Endpoints:
- `POST /api/bugs` - Create new bug
- `GET /api/bugs` - Get all bugs
- `GET /api/bugs/{id}` - Get specific bug
- `GET /api/bugs/project/{projectId}` - Get bugs by project
- `GET /api/bugs/assignee/{assigneeId}` - Get bugs by assignee
- `GET /api/bugs/status/{status}` - Get bugs by status
- `GET /api/bugs/project/{projectId}/status/{status}` - Get bugs by project and status
- `PUT /api/bugs/{id}` - Update bug
- `PATCH /api/bugs/{id}/status` - Update bug status
- `PATCH /api/bugs/{id}/assign` - Assign bug to user
- `POST /api/bugs/{id}/comments` - Add comment
- `PATCH /api/bugs/{id}/priority` - Update priority
- `DELETE /api/bugs/{id}` - Delete bug

## Example JSON Requests:

**Create Bug:**
```json
{
    "title": "Login button not responding",
    "description": "When clicking login button, nothing happens",
    "priority": "HIGH",
    "projectId": "project123",
    "tags": ["ui", "login"]
}
```

**Update Status:**
```json
{
    "status": "IN_PROGRESS",
    "comment": "Started investigating the issue"
}
```

**Assign Bug:**
```json
{
    "assigneeId": "developer123"
}
```

**Add Comment:**
```json
{
    "content": "Found the root cause - missing event listener",
    "authorName": "John Doe"
}
```

Spring Security in progress
This implementation provides:

## UserService Features:
- **User registration** with duplicate checking
- **Password encryption** using BCrypt
- **User lookup methods** using both repository methods
- **User updates** with validation
- **Password updates** with encryption
- **Credential validation** for authentication
- **Role-based filtering**

## UserController Endpoints:
- `POST /api/users/register` - Register new user
- `GET /api/users` - Get all users (Admin only)
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/username/{username}` - Get user by username
- `GET /api/users/email/{email}` - Get user by email
- `GET /api/users/exists/username/{username}` - Check if username exists
- `GET /api/users/exists/email/{email}` - Check if email exists
- `GET /api/users/profile` - Get current user profile
- `PUT /api/users/{id}` - Update user
- `PATCH /api/users/{id}/password` - Update password
- `GET /api/users/role/{role}` - Get users by role (Admin only)
- `DELETE /api/users/{id}` - Delete user (Admin only)

## Key Features:
1. **Security**: Passwords are encrypted and never returned in responses
2. **Validation**: Input validation for all user data
3. **Authorization**: Role-based access control using `@PreAuthorize`
4. **Duplicate Prevention**: Checks for existing usernames/emails
5. **Exception Handling**: Custom exceptions for not found and conflicts

## Example JSON Requests:

**Register User:**
```json
{
    "username": "johndoe",
    "email": "john@example.com",
    "password": "password123",
    "role": "DEVELOPER"
}
```

**Update User:**
```json
{
    "username": "johnsmith",
    "email": "johnsmith@example.com",
    "role": "ADMIN"
}
```

**Update Password:**
```json
{
    "newPassword": "newSecurePassword123"
}
```

The implementation fully utilizes your UserRepository methods and provides a complete user management system!
```

The service automatically tracks changes and adds audit comments for status changes, assignments, and priority updates.
