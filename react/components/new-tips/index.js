import React from 'react';
import { injectIntl } from 'react-intl';
import PropTypes from 'prop-types';
import { Tooltip, Icon } from 'choerodon-ui';

import './index.less';

function Tips({ helpText, showHelp, title, popoverClassName, placement }) {
  return (title ? (
    <div className="c7n-infra-tips-wrap">
      {title && <span>{title}</span>}
      {showHelp && (
        <Tooltip
          title={helpText}
          overlayClassName={`c7n-infra-tips-popover ${popoverClassName || ''}`}
          overlayStyle={{ maxWidth: '3.5rem' }}
          placement={placement}
          arrowPointAtCenter
        >
          <Icon type="help c7n-infra-tips-icon-mr" />
        </Tooltip>
      )}
    </div>
  ) : (
    <Tooltip
      title={helpText}
      overlayClassName={`c7n-infra-tips-popover ${popoverClassName || ''}`}
      placement={placement}
      arrowPointAtCenter
    >
      <Icon type="help c7n-infra-tips-icon" />
    </Tooltip>
  ));
}

Tips.propTypes = {
  helpText: PropTypes.string.isRequired,
  title: PropTypes.string,
  showHelp: PropTypes.bool,
  popoverClassName: PropTypes.string,
  placement: PropTypes.string,
};

Tips.defaultProps = {
  showHelp: true,
  placement: 'topRight',
};

export default injectIntl(Tips);
