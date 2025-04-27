Handles authentication, registration, and user profile.

**Endpoints:**

- `POST /auth/register` – Register a new user
- `POST /auth/login` – Login and get token and store in redis with ttl
- `POST /auth/logout` – Logout user; delete token from redis
- `GET /users/me` – Get current user profile
- `PUT /users/me` – Update user profile
- `POST /auth/verify-token` – Validate token with redis,
- 'POST /auth/has-authority' - validates token whether token is valid and has authority of parameter provided in request header ; This would an internal service

**Internal interface (to other services):**

- Validate token
- Get user info by ID/email

user: {
name: ...,
email: ...
password: ...,
roles: [
ADMIN,
USER
]
}

![](./svgs/auth-flow.svg)
