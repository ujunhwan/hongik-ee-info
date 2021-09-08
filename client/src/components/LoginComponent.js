import React from 'react'

function LoginComponent({
    onSubmitHandler,
    onIdHandler,
    onPasswordHandler,
}) {
    return (
        <>
            <form onSubmit={onSubmitHandler}>
                <div>
                <label>ID</label>
                <input
                    name="id"
                    type="text"
                    placeholder="학번"
                    onChange={onIdHandler}
                />
                </div>
                <div>
                <label>PASSWORD</label>
                <input
                    name="pw"
                    type="password"
                    placeholder="비밀번호"
                    onChange={onPasswordHandler}
                />
                <button type="submit">LOGIN</button>
                </div>
            </form>
        </>
    )
}


export default LoginComponent