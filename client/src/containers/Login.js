import axios from 'axios'
import React, { useState } from 'react'
import LoginComponent from '../components/LoginComponent';
import { setCookie } from '../utils/cookie';

function LoginPageContainer() {

    const [loginInfo, setLoginInfo] = useState({
        USER_ID: "",
        PASSWD: "",
    })

    const config = {
        withCredentials: true,
    }

    const onSubmitHandler = (event) => {
        event.preventDefault();
        console.log(loginInfo);

        const url = "/api/user/login/"

        axios.post(url, loginInfo, config)
        .then((res) => {
            let cookieObj = res.data;
            console.log(cookieObj);

            // for(let key in cookieObj) {
            //     setCookie(key, cookieObj[key]);
            // }

        })
    }

    const onIdHandler = (event) => {
        setLoginInfo({
            ...loginInfo,
            USER_ID: event.currentTarget.value
        })
    }

    const onPasswordHandler = (event) => {
        setLoginInfo({
            ...loginInfo,
            PASSWD: event.currentTarget.value
        })
    }

    return (
        <>
            <LoginComponent 
                onSubmitHandler={onSubmitHandler}
                onIdHandler={onIdHandler}
                onPasswordHandler={onPasswordHandler}
            />
        </>
    )
}

export default LoginPageContainer
