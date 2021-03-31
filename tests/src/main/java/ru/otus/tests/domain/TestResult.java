package ru.otus.tests.domain;

import ru.otus.tests.service.MessageManager;

public enum TestResult {
    SUCCESSFUL {
        @Override
        public String getMessage(MessageManager messageManager) {
            return messageManager.getMessage("test.successful");
        }
    },
    FAIL {
        @Override
        public String getMessage(MessageManager messageManager) {
            return messageManager.getMessage("test.fail");
        }
    };

    public abstract String getMessage(MessageManager messageManager);
}
