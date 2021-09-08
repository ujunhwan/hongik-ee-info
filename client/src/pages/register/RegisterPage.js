import React, { Component } from 'react'
import { withRouter } from 'react-router';
import PageTemplate from '../../components/common/PageTemplate';
import RegisterContainer from '../../containers/register/RegisterContainer'

function RegisterPage() {
    return (
        <PageTemplate>
            <RegisterContainer />
        </PageTemplate>
    );
}

export default withRouter(RegisterPage);