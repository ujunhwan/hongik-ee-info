import React, { Fragment } from "react";
import PropTypes from "prop-types";

import "./PageTemplate.scss";

const PageTemplate = (props) => {
  const { header, children } = props;
  return (
    <Fragment>
      <div className="PageTemplate">
        {header}
        <main className="contents-wrapper">{children}</main>
      </div>
    </Fragment>
  );
};

PageTemplate.propTypes = {
  header: PropTypes.node,
  children: PropTypes.node,
};

export default PageTemplate;
